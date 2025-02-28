package org.example.URLShortener.model;

import lombok.*;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    private String userID;
    private String userName;
    private String userEmailID;
    private String userPassword;
}
