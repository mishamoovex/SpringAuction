package com.lead.service.model.request;

import com.lead.core.constants.GlobalConst;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
public class RegistrationRequest {
    @NotBlank
    @Size(min = 2, max = 30, message = "Should be at most 30 characters long")
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @Size(min = 6, max = 50, message = "Password must be at least 6 and at most 50 characters long")
    private String password;
}
