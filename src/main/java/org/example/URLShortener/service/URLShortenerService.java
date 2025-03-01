package org.example.URLShortener.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    DataStoreService dataStoreService;

    public ResponseEntity<URLResponse> processShortening(URLRequest urlRequest){
        URLResponse urlResponse = new URLResponse();
        ResponseEntity<URLResponse> responseEntity = null;
        Instant now = Instant.now();
        Instant futureTime = null;

        String tinyURL = null;
        String longURL = urlRequest.getLongURL();

        if(StringUtils.isNotBlank(urlRequest.getCustomURL()))
            tinyURL = urlRequest.getCustomURL();
        else
            tinyURL = getTinyURL(longURL);

        // Expiry Time Calculation
        if(StringUtils.isNotBlank(urlRequest.getExpiryTimeStamp()))
            futureTime = now.plus(Long.parseLong(urlRequest.getExpiryTimeStamp()), ChronoUnit.DAYS);
        else
            futureTime = now.plus(urlConfigLoader.getUrlExpiryTime(), ChronoUnit.DAYS);

        responseEntity = mapEntityResponse(urlRequest, tinyURL, futureTime, now);
//        Created responseEntity and stored in dynamoDB
        log.info("the Response Entity Structure is = {}", responseEntity);
        saveURLResponse(responseEntity.getBody());

        return responseEntity;
    }

    private static ResponseEntity<URLResponse> mapEntityResponse(URLRequest urlRequest, String tinyURL, Instant futureTime, Instant now) {
        ResponseEntity<URLResponse> responseEntity;
        responseEntity =  new ResponseEntity<>(URLResponse.builder()
                .longURL(urlRequest.getLongURL())
                .user_Id(urlRequest.getUser_Id())
                .tinyURL(tinyURL) // Needs to create
                .expiryTimeStamp(Date.from(futureTime)) // Needs to map
                .createdTimeStamp(Date.from(now))
                .build(),HttpStatus.ACCEPTED);
        return responseEntity;
    }

    // Algorithm -> It is not secure just using for the Implementation pupose.

    // Step-01  MD5 hash of the long URL -> Will generate the hexaDecimal Values
    // Step -02 Take the first few bits
    // Step -03 Convert these bytes to decimal
    // Step -04 Encode the result into a Base62 encoded string: DZFbb43
    // And return the string

    private  String getTinyURL(String input) {
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
        dataStoreService.saveURLs(urlResponse);
        return "data added to dynamoDB SuccessFully.";
    }

    public URLResponse fetchURLResponse(String tinyURL){
        return dataStoreService.getItemByTinyURL(tinyURL);
    }

}
