package com.lead.service.user.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class UserDetailsDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
