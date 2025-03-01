package org.example.URLShortener.service;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.model.URLResponse;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.sql.Date;
import java.util.Map;

@Slf4j
@Service
public class DataStoreService {
    private static final String TABLE_NAME = "urlResponseTable";
    private final DynamoDbClient dynamoDbClient;

    public DataStoreService(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void saveURLs(URLResponse urlResponse) {
        PutItemRequest putItemRequest = getPutItemRequest(urlResponse);
        try {
            dynamoDbClient.putItem(putItemRequest);
        } catch (Exception e) {
            log.error("Error saving to DynamoDB: {}", e.getMessage(), e);
        }
    }

    private static PutItemRequest getPutItemRequest(URLResponse urlResponse) {
        return PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(Map.of(
                        "longURL", AttributeValue.builder().s(urlResponse.getLongURL()).build(),
                        "tinyURL", AttributeValue.builder().s(urlResponse.getTinyURL()).build(),
                        "expiryTimeStamp", AttributeValue.builder().s(String.valueOf(urlResponse.getExpiryTimeStamp())).build(),
                        "createdTimeStamp", AttributeValue.builder().s(String.valueOf(urlResponse.getCreatedTimeStamp())).build(),
                        "user_Id", AttributeValue.builder().s(urlResponse.getUser_Id()).build()
                ))
                .returnConsumedCapacity(ReturnConsumedCapacity.TOTAL)
                .build();
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
            GetItemResponse  response = dynamoDbClient.getItem(getItemRequest);
            log.info("gettint the response from the DynamoDB = {}",response);
            return getURLResponseFromGetItemRequest(response);
        }catch (Exception e){
            log.info("Getting issue in GetInput ");
        }
        return null;
    }

    private static URLResponse getURLResponseFromGetItemRequest(GetItemResponse response) {
        if (response.hasItem()) {
            Map<String, AttributeValue> item = response.item();
            log.info("The item received from the DB = {}", item);

            URLResponse urlResponse = new URLResponse();
            urlResponse.setTinyURL(item.get("tinyURL").s());
            urlResponse.setUser_Id(item.get("user_Id").s());
            urlResponse.setLongURL(item.get("longURL").s());
            urlResponse.setExpiryTimeStamp(Date.valueOf(item.get("expiryTimeStamp").s()));
            urlResponse.setCreatedTimeStamp(Date.valueOf(item.get("createdTimeStamp").s()));

            return urlResponse;
        } else {
            log.warn("Item with TinyURL {} not found.", response);
            return null;
        }
    }
}
