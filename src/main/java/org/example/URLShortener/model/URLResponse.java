package org.example.URLShortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class URLResponse {
    private String longURL;
    private String tinyURL;
    private String expiryTimeStamp; // in days
    private String createdTimeStamp;
    private String user_Id;
}
