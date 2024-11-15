package com.lead.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthTokenDetails {
    private String username;
    private Boolean isExpired;
    private AuthTokenType authTokenType;
}
