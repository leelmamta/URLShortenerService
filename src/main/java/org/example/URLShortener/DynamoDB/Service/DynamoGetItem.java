package org.example.URLShortener.DynamoDB.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.model.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

import static org.example.URLShortener.DynamoDB.constants.TableConstants.TABLE_NAME;
@Slf4j
@Service
public class DynamoGetItem {
    @Autowired
    DynamoTable dynamoTable;
    private final DynamoDbClient dynamoDbClient;

    public DynamoGetItem(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
    public URLResponse getItemByTinyURL(String tinyURL) {
        Map<String, AttributeValue> key = Map.of(
                "tinyURL", AttributeValue.builder().s(tinyURL).build());

        GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(key)
                .build();

        log.info("GetItemRequest = {}", getItemRequest);
        try {
            // Check if table exists or not
            dynamoTable.checkTable();
            GetItemResponse response = dynamoDbClient.getItem(getItemRequest);
            log.info("getting the response from the DynamoDB = {}",response);
            return mapGetItemResponse(response);
        }catch (Exception e){
            log.info("Getting issue in GetInput ");
        }
        return null;
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
