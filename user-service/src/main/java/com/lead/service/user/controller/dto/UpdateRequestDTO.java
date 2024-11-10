package com.lead.service.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class UpdateRequestDTO {
    @NotBlank(message = "User first name is required")
    private String firstName;
    @NotBlank(message = "User last name is required")
    private String lastName;
}
