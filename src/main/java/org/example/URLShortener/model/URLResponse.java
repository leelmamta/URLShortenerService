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
    private Date expiryTimeStamp; // in days
    private Date createdTimeStamp;
    private String User_Id;
}
