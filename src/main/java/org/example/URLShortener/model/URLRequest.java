package org.example.URLShortener.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class URLRequest {
        private String longURL;
        private String customURL;
        private String expiryTimeStamp; // in days
        private String user_Id;
}
