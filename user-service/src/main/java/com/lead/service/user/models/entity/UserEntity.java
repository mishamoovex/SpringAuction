package com.lead.service.user.models.entity;

import com.lead.core.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_account")
public class UserEntity extends BaseEntity {
    @Column(nullable = false, length = 30)
    private String firstName;
    @Column(nullable = false, length = 30)
    private String lastName;
    @Column(length = 50, nullable = false, unique = true)
    private String email;
    @Column(length = 50, nullable = false)
    private String password;
}
