package com.lead.service.user.service;

import com.lead.exceptions.NotFoundException;
import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.controller.dto.UpdateRequestDTO;
import com.lead.service.user.controller.dto.UserDTO;
import com.lead.service.user.repository.UserRepository;
import com.lead.service.user.repository.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDTO save(RegisterRequestDTO request) {
        UserEntity entity = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        UserEntity newUser = userRepository.save(entity);
        return modelMapper.map(newUser, UserDTO.class);
    }

    @Transactional
    @Override
    public UserDTO update(String id, UpdateRequestDTO request) {
        UserEntity entity = findById(id).toBuilder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public UserDTO updateEmail(String id, String email) {
        UserEntity entity = findById(id).toBuilder()
                .email(email)
                .build();

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDTO getById(String id) {
        return modelMapper.map(findById(id), UserDTO.class);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDTO.class))
                .toList();
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
