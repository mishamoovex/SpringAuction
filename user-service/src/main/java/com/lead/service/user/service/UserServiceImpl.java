package com.lead.service.user.service;

import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.exceptions.NotFoundException;
import com.lead.service.user.repository.entity.UserEntity;
import com.lead.service.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Transactional
    @Override
    public UserEntity save(RegisterRequestDTO request) {
        UserEntity entity = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        return userRepository.save(entity);
    }

    @Transactional
    @Override
    public UserEntity update(UserEntity user) {
        UserEntity currentUser = findById(user.getId());
        return userRepository.save(currentUser);
    }

    @Override
    public UserEntity getById(String id) {
        return findById(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(String id) {
        UserEntity currentUser = findById(id);
        userRepository.deleteById(currentUser.getId());
    }

    private UserEntity findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id:" + id + " not found"));
    }
}
