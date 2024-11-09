package com.lead.service.user.service;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.exception.NotFoundException;
import com.lead.service.user.model.User;
import com.lead.service.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User save(RegisterRequestDTO request) {
        User entity = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        return userRepository.save(entity);
    }

    @Override
    public User update(User user) {
        User currentUser = findById(user.getId());
        return userRepository.save(currentUser);
    }

    @Override
    public User getById(String id) {
        return findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void delete(String id) {
        User currentUser = findById(id);
        userRepository.delete(currentUser);
    }

    private User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id:" + id + " not found"));
    }
}
