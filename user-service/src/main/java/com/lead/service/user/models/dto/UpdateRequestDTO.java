package com.lead.service.user.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class UpdateRequestDTO {
    @NotBlank(message = "User first name is required")
    private String firstName;
    @NotBlank(message = "User last name is required")
    private String lastName;
}
