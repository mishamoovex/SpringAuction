package com.lead.service.user.service;

import com.lead.exceptions.NotFoundException;
import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.model.User;
import com.lead.service.user.repository.UserRepository;
import com.lead.service.util.UserUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private UserServiceImpl objectUnderTest;

    @BeforeEach
    public void setUp() {
        objectUnderTest = new UserServiceImpl(userRepository);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(userRepository);
    }

    @Nested
    @DisplayName("save a new user")
    class WhenSaveNewUser {

        @Test
        public void shouldSaveWhenDataIsValid() {
            //Given
            RegisterRequestDTO registerRequest = new RegisterRequestDTO("Jon", "Smith", "email@gmail.com", "123456");
            User newUser = createTestUser();

            when(userRepository.save(any(User.class))).thenReturn(newUser);

            //When
            User actualUser = objectUnderTest.save(registerRequest);

            //Than
            assertThat(actualUser).isEqualTo(newUser);
        }

    }

    @Nested
    @DisplayName("when update user")
    class WhenUpdateUser {

        @Test
        public void shouldUpdateUserWhenDataIsValid() {
            //Given
            User existingUser = createTestUser();
            User updateUser = createTestUser("NewLastName");
            User updatedUser = UserUtil.createUpdatedTestUser("NewLastName");

            when(userRepository.findById("id")).thenReturn(Optional.of(existingUser));
            when(userRepository.save(any(User.class))).thenReturn(updatedUser);

            //When
            User actualUser = objectUnderTest.update(updateUser);

            //Than
            assertThat(actualUser).isEqualTo(updatedUser);
        }

        @Test
        public void shouldThrowNotFoundExceptionWhenIsNotFound() {
            //Given
            User updateUser = createTestUser();

            //Than
            assertThatThrownBy(() -> objectUnderTest.update(updateUser))
                    .isInstanceOf(NotFoundException.class);
        }
    }

    @Nested
    @DisplayName("when delete user")
    class WhenDeleteUser {

        @Test
        public void shouldDeleteUserWhenExists() {
            //Given
            User existingUser = createTestUser();

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
            User existingUser = createTestUser();

            when(userRepository.findById("id")).thenReturn(Optional.of(existingUser));

            //When
            User actualUser = objectUnderTest.getById("id");

            //Than
            assertThat(actualUser).isEqualTo(existingUser);
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
            List<User> existingUsers = UserUtil.createTestUsers(3);
            when(userRepository.findAll()).thenReturn(existingUsers);

            //When
            List<User> actualUsers = objectUnderTest.getAll();

            //Than
            assertThat(actualUsers).containsExactlyElementsOf(existingUsers);
        }

        @Test
        public void shouldReturnEmptyListWhenNoUsersFound() {
            //Given
            when(userRepository.findAll()).thenReturn(new ArrayList<>());

            //When
            List<User> actualUsers = userRepository.findAll();

            //Than
            assertThat(actualUsers).isEmpty();
        }
    }
}
