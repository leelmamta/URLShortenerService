package org.example.URLShortener.DynamoDB.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import static org.example.URLShortener.DynamoDB.constants.TableConstants.TABLE_NAME;
@Slf4j
@Component
public class DynamoTable {
    private final DynamoDbClient dynamoDbClient;

    public DynamoTable(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void createTable(){
        log.info("creating the table = {}",TABLE_NAME);
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName(TABLE_NAME)
                .keySchema(
                        KeySchemaElement.builder().attributeName("tinyURL").keyType(KeyType.HASH).build() // Partition Key
                )
                .attributeDefinitions(
                        AttributeDefinition.builder().attributeName("tinyURL").attributeType(ScalarAttributeType.S).build())
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();
        dynamoDbClient.createTable(request);
    }
    public void checkTable(){
        try{
            ScanRequest scanRequest = ScanRequest.builder().tableName(TABLE_NAME).build();
            ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
            System.out.println("Table Items: " + scanResponse.items());
        }catch (Exception e){
            log.error("table doesn't exists or table scan failed");
        }
    }
}
