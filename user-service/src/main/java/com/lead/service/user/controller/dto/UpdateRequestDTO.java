package com.lead.service.user.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public final class UpdateRequestDTO {
    @NotBlank(message = "User first name is required")
    private String firstName;
    @NotBlank(message = "User last name is required")
    private String lastName;
}
