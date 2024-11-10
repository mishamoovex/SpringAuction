package com.lead.service.user.service;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.repository.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity save(RegisterRequestDTO request);

    UserEntity update(UserEntity user);

    UserEntity getById(String id);

    List<UserEntity> getAll();

    void delete(String id);
}
