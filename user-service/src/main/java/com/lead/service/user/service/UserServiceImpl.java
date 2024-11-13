package com.lead.service.user.service;

import com.lead.core.exception.NotFoundException;
import com.lead.service.user.models.request.RegisterRequest;
import com.lead.service.user.models.request.UpdateRequest;
import com.lead.service.user.models.dto.UserDetailsDto;
import com.lead.service.user.models.dto.UserDto;
import com.lead.service.user.models.entity.UserEntity;
import com.lead.service.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDetailsDto save(RegisterRequest request) {
        UserEntity entity = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserEntity newUser = userRepository.save(entity);
        return modelMapper.map(newUser, UserDetailsDto.class);
    }

    @Transactional
    @Override
    public UserDto update(String id, UpdateRequest request) {
        UserEntity entity = findById(id);

        //TODO What is the right way to validate fields to updated and service input fields in general???
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Transactional
    @Override
    public UserDto updateEmail(String id, String email) {
        UserEntity entity = findById(id);
        //Should I validate an email here?
        entity.setEmail(email);

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getById(String id) {
        return modelMapper.map(findById(id), UserDto.class);
    }

    @Override
    public UserDetailsDto getUserDetailsByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));

        return modelMapper.map(user, UserDetailsDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .toList();
    }

    @Transactional
    @Override
    public void delete(String id) {
        //TODO Do I need to check if user exists here on OR in the Controller Authorization?
        UserEntity currentUser = findById(id);
        userRepository.deleteById(currentUser.getId());
    }

    private UserEntity findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id:" + id + " not found"));
    }
}
