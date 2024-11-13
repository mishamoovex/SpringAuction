package com.lead.service.user.models.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class RegisterRequest {
    @NotBlank
    private String firstName;
    @NotBlank(message = "User last name is required")
    private String lastName;
    @Email
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
