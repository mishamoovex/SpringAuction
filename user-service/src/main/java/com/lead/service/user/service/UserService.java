package com.lead.service.user.service;

import com.lead.service.user.models.request.RegisterRequest;
import com.lead.service.user.models.request.UpdateRequest;
import com.lead.service.user.models.dto.UserAccountDto;
import com.lead.service.user.models.dto.UserDto;

import java.util.List;

//TODO Should I use nullable annotations to identify
// that response in NOT NULL or NULLABLE
public interface UserService {
    UserAccountDto save(RegisterRequest request);

    UserDto update(String id, UpdateRequest request);

    UserDto updateEmail(String id, String email);

    UserDto getById(String id);

    UserAccountDto getAccountByEmail(String email);

    List<UserDto> getAll();

    void delete(String id);
}
