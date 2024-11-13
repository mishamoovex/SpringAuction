package com.lead.service.user.service;

import com.lead.service.user.models.dto.RegisterRequestDTO;
import com.lead.service.user.models.dto.UpdateRequestDTO;
import com.lead.service.user.models.dto.UserAccountDto;
import com.lead.service.user.models.dto.UserDTO;

import java.util.List;

//TODO Should I use nullable annotations to identify
// that response in NOT NULL or NULLABLE
public interface UserService {
    UserAccountDto save(RegisterRequestDTO request);

    UserDTO update(String id, UpdateRequestDTO request);

    UserDTO updateEmail(String id, String email);

    UserDTO getById(String id);

    UserAccountDto getAccountByEmail(String email);

    List<UserDTO> getAll();

    void delete(String id);
}
