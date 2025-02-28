package org.example.URLShortener.model;

import lombok.*;

import java.text.DateFormat;

@Data
@Builder
public class URLMapping {
    private String longURL;
    private String shortURL;
    private DateFormat createdAtTimestamp;
    private DateFormat expirationTimestamp;
    private String userId;
    private long clickCount;
}
