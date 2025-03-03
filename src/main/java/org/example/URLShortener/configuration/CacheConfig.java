package org.example.URLShortener.configuration;

import lombok.extern.slf4j.Slf4j;
import org.example.URLShortener.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {
//     Basically we have a cacheManager that stores the cached information
    // we have to name the cacheNames too
    @Autowired
    CacheService cacheService;
    public void refreshURLResponseCache(){
           cacheService.loadURLResponseCache("tinyURL");
    }
}
