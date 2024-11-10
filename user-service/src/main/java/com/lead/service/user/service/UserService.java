package com.lead.service.user.service;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.controller.dto.UpdateRequestDTO;
import com.lead.service.user.controller.dto.UserDTO;
import com.lead.service.user.repository.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDTO save(RegisterRequestDTO request);

    UserDTO update(String id, UpdateRequestDTO request);

    UserDTO updateEmail(String id, String email);

    UserDTO getById(String id);

    List<UserDTO> getAll();

    void delete(String id);
}
