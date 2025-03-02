package org.example.URLShortener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.URLShortener.DynamoDB.Service.DynamoGetItem;
import org.example.URLShortener.DynamoDB.Service.DynamoPutItem;
import org.example.URLShortener.Utils.Utils;
import org.example.URLShortener.configuration.URLConfigLoader;
import org.example.URLShortener.model.URLRequest;
import org.example.URLShortener.model.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;

@Slf4j
@Service
public class URLShortenerService {

    @Autowired
    URLConfigLoader urlConfigLoader;

    @Autowired
    DynamoGetItem dynamoGetItem;

    @Autowired
    DynamoPutItem dynamoPutItem;

    public ResponseEntity<URLResponse> processShortening(URLRequest urlRequest){
        URLResponse urlResponse = new URLResponse();
        ResponseEntity<URLResponse> responseEntity = null;
        Instant now = Instant.now();

        String tinyURL = null;
        String longURL = urlRequest.getLongURL();
        // tiny URL -> customization and calculation
        if(StringUtils.isNotBlank(urlRequest.getCustomURL()))
            tinyURL = urlRequest.getCustomURL();
        else
            tinyURL = createTinyURLUsingMD5(longURL);

        // Expiry Time Calculation
        Instant  futureTime = getExpiryTimeStamp(urlRequest, now);

        responseEntity  =  new ResponseEntity<>(URLResponse.builder()
                .longURL(urlRequest.getLongURL())
                .user_Id(urlRequest.getUser_Id())
                .tinyURL(tinyURL)
                .expiryTimeStamp(String.valueOf(futureTime))
                .createdTimeStamp(String.valueOf(now))
                .build(),HttpStatus.ACCEPTED);
        log.info("the Response Entity Structure is = {}", responseEntity);
        saveURLResponse(responseEntity.getBody());
        return responseEntity;
    }
    private Instant getExpiryTimeStamp(URLRequest urlRequest, Instant now) {
        Instant futureTime;
        if(StringUtils.isNotBlank(urlRequest.getExpiryTimeStamp()))
            futureTime = now.plus(Long.parseLong(urlRequest.getExpiryTimeStamp()), ChronoUnit.DAYS);
        else
            futureTime = now.plus(urlConfigLoader.getUrlExpiryTime(), ChronoUnit.DAYS);
        return futureTime;
    }
    private  String createTinyURLUsingMD5(String input) {
        String tinyURL = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            byte[] first6Bytes = Arrays.copyOfRange(hash, 0, 6);

            long decimalValue = 0;
            for (byte b : first6Bytes) {
                decimalValue = (decimalValue << 8) | (b & 0xFF);
            }
            tinyURL = Utils.toBase62(decimalValue);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
        return tinyURL ;
    }

    private String saveURLResponse(URLResponse urlResponse){
        log.info("Saving the URLResponse into the db.");
        dynamoPutItem.saveURLs(urlResponse);
        return "data added to dynamoDB SuccessFully.";
    }

    public URLResponse fetchURLResponse(String tinyURL){
        return dynamoGetItem.getItemByTinyURL(tinyURL);
    }

}
