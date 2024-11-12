package com.lead.service.user.models.dto;

import com.lead.core.constants.GlobalConst;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class RegisterRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank(message = "User last name is required")
    private String lastName;
    @Email
    @Pattern(regexp = GlobalConst.REGEX_EMAIL, message = "Email is not valid")
    private String email;
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}
