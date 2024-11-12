package com.lead.service.model.response;

import com.lead.service.model.dto.TokenDto;
import com.lead.service.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthResponse {
    private UserDto user;
    private TokenDto token;
}
