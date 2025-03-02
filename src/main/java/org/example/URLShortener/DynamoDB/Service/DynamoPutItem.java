package org.example.URLShortener.DynamoDB.Service;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.DynamoDB.utils.DynamoHelper;
import org.example.URLShortener.model.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.Map;

import static org.example.URLShortener.DynamoDB.constants.TableConstants.TABLE_NAME;

@Slf4j
@Service
public class DynamoPutItem {

    @Autowired
    DynamoTable dynamoTable;
    private final DynamoDbClient dynamoDbClient;

    public DynamoPutItem(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
    public void saveURLs(URLResponse urlResponse) {
        try {
          boolean doesTableExist =  DynamoHelper.doesTableExist(dynamoDbClient,TABLE_NAME);
          log.info("in SaveURLS: -> ",doesTableExist);
            if(!doesTableExist)
                dynamoTable.createTable();
            PutItemRequest putItemRequest = getPutItemRequest(urlResponse);
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
}
