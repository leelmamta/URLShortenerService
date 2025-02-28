package org.example.URLShortener.controller;

import org.example.URLShortener.service.URLShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/URLShortener")
public class APIController {
    @Autowired
    URLShortenerService urlShortenerService;

    @PostMapping("/v1/publish")
    String mapURLShortner(@RequestBody String requestBody, @RequestHeader Map<String, String> requestHeaders){
        return requestBody;
    }
}
