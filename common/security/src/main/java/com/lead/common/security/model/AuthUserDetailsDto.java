package com.lead.common.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class AuthUserDetailsDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
