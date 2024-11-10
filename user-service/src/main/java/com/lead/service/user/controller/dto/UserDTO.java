package com.lead.service.user.controller.dto;

public record UserDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        String password
) {
}
