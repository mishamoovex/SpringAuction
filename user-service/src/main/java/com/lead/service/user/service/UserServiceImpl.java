package com.lead.service.user.service;

import com.lead.core.exception.NotFoundException;
import com.lead.service.user.models.dto.RegisterRequestDTO;
import com.lead.service.user.models.dto.UpdateRequestDTO;
import com.lead.service.user.models.dto.UserDTO;
import com.lead.service.user.repository.UserRepository;
import com.lead.service.user.models.entity.UserEntity;
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
        UserEntity entity = findById(id);

        //TODO What is the right way to validate fields to updated and service input fields in general???
        if (request.getFirstName() != null) {
            entity.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            entity.setLastName(request.getLastName());
        }

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public UserDTO updateEmail(String id, String email) {
        UserEntity entity = findById(id);
        entity.setEmail(email);

        UserEntity updatedUser = userRepository.save(entity);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public UserDTO getById(String id) {
        return modelMapper.map(findById(id), UserDTO.class);
    }

    @Override
    public UserDTO getByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));

        return modelMapper.map(user, UserDTO.class);
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
        //TODO Do I need to check if user exists here on OR in the Controller Authorization?
        UserEntity currentUser = findById(id);
        userRepository.deleteById(currentUser.getId());
    }

    private UserEntity findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id:" + id + " not found"));
    }
}
