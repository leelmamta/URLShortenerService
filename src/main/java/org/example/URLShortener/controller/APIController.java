package org.example.URLShortener.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.model.URLRequest;
import org.example.URLShortener.model.URLResponse;
import org.example.URLShortener.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.example.URLShortener.constants.URLConstants.EXCEPTION_MESSAGE;
@Slf4j
@RestController
@RequestMapping("/URLShortener")
public class APIController {
    @Autowired
    URLShortenerService urlShortenerService;

    @PostMapping("/v1/publish")
    ResponseEntity<URLResponse> mapURLShortner(@RequestBody String requestBody, @RequestHeader Map<String, String> requestHeaders) throws Exception {
        log.info("Processing the request = {}", requestBody);
        ResponseEntity<URLResponse> responseResponseEntity = null;
        StopWatch stopWatch = null;
        URLRequest urlRequest = null;
        JsonNode rootNode = null;
        try{
            stopWatch = new StopWatch();
            stopWatch.start();
            urlRequest = new ObjectMapper().readValue(requestBody,URLRequest.class);
            responseResponseEntity = urlShortenerService.processShortening(urlRequest);
            stopWatch.stop();
        }catch(Exception e){
           throw  new Exception(EXCEPTION_MESSAGE);
        }

        return responseResponseEntity;
    }
}
