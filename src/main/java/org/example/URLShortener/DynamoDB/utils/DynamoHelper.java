package org.example.URLShortener.DynamoDB.utils;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DescribeTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.ResourceNotFoundException;

public class DynamoHelper {
    public static boolean doesTableExist(DynamoDbClient dynamoDbClient, String tableName) {
        try {
            dynamoDbClient.describeTable(DescribeTableRequest.builder().tableName(tableName).build());
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        } catch (DynamoDbException e) {
            System.err.println("Error checking table: " + e.getMessage());
            return false;
        }
    }

}
