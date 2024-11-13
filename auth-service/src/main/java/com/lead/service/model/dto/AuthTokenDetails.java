package com.lead.service.model.dto;

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
