package org.example.URLShortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.service.URLShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

@Slf4j
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness= Strictness.LENIENT)
public class APIControllerTest {
    @InjectMocks
    APIController apiController;

    @Mock
    URLShortenerService urlShortenerService;

    @BeforeEach
    void setupTest(){
        apiController = new APIController();
        ReflectionTestUtils.setField(apiController, "urlShortenerService", urlShortenerService);
    }

    @Test
    void testAPIController(){
        try {
            apiController.shortenURL("{}", new HashMap<>());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
