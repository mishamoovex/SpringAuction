package com.lead.service.user.service;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.model.User;

import java.util.List;

public interface UserService {
    User save(RegisterRequestDTO request);

    User update(User user);

    User getById(String id);

    List<User> getAll();

    void delete(String id);
}
