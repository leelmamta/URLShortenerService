package org.example.URLShortener.DynamoDB.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.model.URLResponse;
import org.example.URLShortener.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;
import java.util.Objects;

import static org.example.URLShortener.DynamoDB.constants.TableConstants.TABLE_NAME;
import static org.example.URLShortener.constants.URLConstants.URL_RESPONSE_CACHE;
import static org.example.URLShortener.constants.URLConstants.URL_RESPONSE_MAP_CACHE;

@Slf4j
@Service
public class DynamoGetItem {
    @Autowired
    CacheService cacheService;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    DynamoTable dynamoTable;
    private final DynamoDbClient dynamoDbClient;

    public DynamoGetItem(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
    public URLResponse getItemByTinyURL(String tinyURL) {
        // First lets use the cache and then hit the DB request
       URLResponse urlResponse = null;
             urlResponse =   cacheService.retrieveURLResponseCache();
       log.info("retrieved Cache URL response is = {}", urlResponse);
       if(urlResponse.getLongURL()==null){
           Map<String, AttributeValue> key = Map.of(
                   "tinyURL", AttributeValue.builder().s(tinyURL).build());

           GetItemRequest getItemRequest = GetItemRequest.builder()
                   .tableName(TABLE_NAME)
                   .key(key)
                   .build();

           log.info("the prepared GetItemRequest = {}", getItemRequest);
           try {
               // Check if table exists or not
               dynamoTable.checkTable();
               GetItemResponse response = dynamoDbClient.getItem(getItemRequest);
               log.info("getting the response from the DynamoDB is = {} for getItemResponse = {}",response, response);
               urlResponse =  mapGetItemResponse(response);
               Objects.requireNonNull(cacheManager.getCache(URL_RESPONSE_CACHE)).put(URL_RESPONSE_MAP_CACHE,urlResponse);
           }catch (Exception e){
               log.info("Getting issue in GetInput ");
           }

       }
        return urlResponse;
    }

    private static URLResponse mapGetItemResponse(GetItemResponse response) {
        log.info("item is = {}", response);
        URLResponse urlResponse = new URLResponse();
        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            urlResponse.setTinyURL(item.get("tinyURL").s());
            urlResponse.setUser_Id(item.get("user_Id").s());
            urlResponse.setLongURL(item.get("longURL").s());
            urlResponse.setExpiryTimeStamp(item.get("expiryTimeStamp").s());
            urlResponse.setCreatedTimeStamp(item.get("createdTimeStamp").s());
        } else {
            log.warn("Item with TinyURL {} not found.", response);
        }
        return urlResponse;
    }
}
