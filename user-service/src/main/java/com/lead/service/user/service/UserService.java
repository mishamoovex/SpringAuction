package com.lead.service.user.service;

import com.lead.service.user.models.dto.UserDetailsDto;
import com.lead.service.user.models.dto.UserDto;
import com.lead.service.user.models.request.RegisterRequest;
import com.lead.service.user.models.request.UpdateRequest;

import java.util.List;

public interface UserService {
    UserDetailsDto save(RegisterRequest request);

    UserDto update(String id, UpdateRequest request);

    UserDto updateEmail(String id, String email);

    UserDto getById(String id);

    UserDetailsDto getUserDetailsByEmail(String email);

    List<UserDto> getAll();

    void delete(String id);
}
