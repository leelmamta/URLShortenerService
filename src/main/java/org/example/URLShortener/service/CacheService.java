package org.example.URLShortener.service;

import org.example.URLShortener.model.URLResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import static org.example.URLShortener.constants.URLConstants.URL_RESPONSE_CACHE;
import static org.example.URLShortener.constants.URLConstants.URL_RESPONSE_MAP_CACHE;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;
    public void loadURLResponseCache(String tinyURL){
        // Need to take the data from the DB and store into the cacheManager


    }
    public URLResponse retrieveURLResponseCache(){
        Cache.ValueWrapper valueWrapper = cacheManager.getCache(URL_RESPONSE_CACHE).get(URL_RESPONSE_MAP_CACHE);
        return valueWrapper!=null?(URLResponse) valueWrapper.get():new URLResponse();
    }

}
