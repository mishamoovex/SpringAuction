package com.lead.service.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class LoginRequest {
    @Email
    private String email;
    @Size(min = 6, max = 50, message = "Password must be at least 6 and at most 50 characters long")
    private String password;
}
