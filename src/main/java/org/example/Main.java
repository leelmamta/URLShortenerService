package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableCaching
public class Main {
    public static void main(String[] args) {

//        @SpringBootApplication -> @Configuration, @EnableAutoConfiguration, and @ComponentScan
        SpringApplication.run(Main.class, args);
//        SpringApplication.BANNER_LOCATION_PROPERTY_VALUE
    }
}