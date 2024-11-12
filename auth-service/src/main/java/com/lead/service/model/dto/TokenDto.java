package com.lead.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String authToken;
    private String refreshToken;
}
