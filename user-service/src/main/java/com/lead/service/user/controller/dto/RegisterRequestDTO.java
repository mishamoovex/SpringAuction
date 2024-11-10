package com.lead.service.user.controller.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
