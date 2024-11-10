package com.lead.service.user.service;

import com.lead.exceptions.NotFoundException;
import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.controller.dto.UserDTO;
import com.lead.service.user.repository.UserRepository;
import com.lead.service.user.repository.entity.UserEntity;
import com.lead.service.util.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.lead.service.util.UserUtil.createTestUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private UserServiceImpl objectUnderTest;

    @BeforeEach
    public void setUp() {
        objectUnderTest = new UserServiceImpl(userRepository, modelMapper);
    }

    @Nested
    @DisplayName("save a new user")
    class WhenSaveNewUser {

        @Test
        public void shouldSaveWhenDataIsValid() {
            //Given
            RegisterRequestDTO registerRequest = new RegisterRequestDTO("Jon", "Smith", "email@gmail.com", "123456");

            UserEntity newUser = createTestUser();
            UserDTO expectedUser = modelMapper.map(newUser, UserDTO.class);
            when(userRepository.save(any(UserEntity.class))).thenReturn(newUser);

            //When
            UserDTO actualUser = objectUnderTest.save(registerRequest);

            //Than
            assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
        }

    }

    @Nested
    @DisplayName("when update user")
    class WhenUpdateUser {

//        @Test
//        public void shouldUpdateUserWhenDataIsValid() {
//            //Given
//            UserEntity existingUser = createTestUser();
//            UserEntity updateUser = createTestUser("NewLastName");
//            UserEntity updatedUser = UserUtil.createUpdatedTestUser("NewLastName");
//
//            when(userRepository.findById("id")).thenReturn(Optional.of(existingUser));
//            when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);
//
//            //When
//            UserDTO actualUser = objectUnderTest.update(updateUser);
//
//            //Than
//            assertThat(actualUser).isEqualTo(updatedUser);
//        }

//        @Test
//        public void shouldThrowNotFoundExceptionWhenIsNotFound() {
//            //Given
//            UserEntity updateUser = createTestUser();
//
//            //Than
//            assertThatThrownBy(() -> objectUnderTest.update(updateUser))
//                    .isInstanceOf(NotFoundException.class);
//        }
    }

    @Nested
    @DisplayName("when delete user")
    class WhenDeleteUser {

        @Test
        public void shouldDeleteUserWhenExists() {
            //Given
            UserEntity existingUser = createTestUser();

            when(userRepository.findById("id")).thenReturn(Optional.of(existingUser));

            //When
            objectUnderTest.delete("id");

            //Than
            verify(userRepository).deleteById("id");
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenDoesNotExist() {
            //Given
            when(userRepository.findById("id")).thenReturn(Optional.empty());

            //Than
            assertThatThrownBy(() -> objectUnderTest.delete("id"))
                    .isInstanceOf(NotFoundException.class);
        }
    }

    @Nested
    @DisplayName("when find user")
    class WhenFindUser {

        @Test
        public void shouldReturnUserWhenFound() {
            //Given
            UserEntity existingUser = createTestUser();

            when(userRepository.findById("id")).thenReturn(Optional.of(existingUser));
            UserDTO expectedUser = modelMapper.map(existingUser, UserDTO.class);

            //When
            UserDTO actualUser = objectUnderTest.getById("id");

            //Than
            assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenIsNotFound() {
            //Given
            when(userRepository.findById("id")).thenReturn(Optional.empty());

            //Than
            assertThatThrownBy(() -> objectUnderTest.getById("id"))
                    .isInstanceOf(NotFoundException.class);

        }
    }

    @Nested
    @DisplayName("when find all users")
    class WhenFindAllUsers {

        @Test
        public void shouldReturnUsersWhenFound() {
            //Given
            List<UserEntity> existingUsers = UserUtil.createTestUsers(3);

            when(userRepository.findAll()).thenReturn(existingUsers);
            List<UserDTO> expectedUsers = existingUsers.stream()
                    .map(entity -> modelMapper.map(entity, UserDTO.class))
                    .toList();
            //When
            List<UserDTO> actualUsers = objectUnderTest.getAll();

            //Than
            assertThat(actualUsers).usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedUsers);
        }

        @Test
        public void shouldReturnEmptyListWhenNoUsersFound() {
            //Given
            when(userRepository.findAll()).thenReturn(new ArrayList<>());

            //When
            List<UserEntity> actualUsers = userRepository.findAll();

            //Than
            assertThat(actualUsers).isEmpty();
        }
    }
}
