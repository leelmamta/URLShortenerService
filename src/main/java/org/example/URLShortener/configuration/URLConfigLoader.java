package org.example.URLShortener.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Slf4j
@ToString
@Setter
@Getter
@Configuration
public class URLConfigLoader {
    /**
     * This class will contain the Application.yml defined properties.
     */
    @Value("${api.url.defaultExpiryTime}")
    private long urlExpiryTime;

}
