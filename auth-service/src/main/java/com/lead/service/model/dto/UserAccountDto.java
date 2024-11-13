package com.lead.service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccountDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
