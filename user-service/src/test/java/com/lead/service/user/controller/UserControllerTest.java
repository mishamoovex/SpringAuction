package com.lead.service.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lead.service.user.controller.dto.RegisterRequestDTO;
import com.lead.service.user.model.User;
import com.lead.service.user.service.UserService;
import com.lead.service.util.UserUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.lead.service.util.matchers.ResponseBodyMatchers.responseBody;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Nested
    @DisplayName("WHEN save a new user")
    class WhenSaveNewUser {

        @Test
        public void WHEN_request_valid_SHOULD_return_new_user() throws Exception {
            //Given
            RegisterRequestDTO request = new RegisterRequestDTO("Jon", "Smith", "email@gmail.com", "123456");

            User expectedUser = UserUtil.createTestUser();
            when(userService.save(any(RegisterRequestDTO.class))).thenReturn(expectedUser);

            //When
            mockMvc.perform(post("/v1/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )

                    //Than
                    .andExpect(status().isOk())
                    .andExpect(responseBody().containsObjectAsJson(expectedUser, User.class));
        }

        @Test
        public void WHEN_request_invalid_SHOULD_return_400() throws Exception {
            //Given
            RegisterRequestDTO request = new RegisterRequestDTO("", "", "", "");

            //WHen
            mockMvc.perform(post("/v1/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isBadRequest());

            //Than
            verify(userService, never()).save(request);
        }
    }

    @Nested
    @DisplayName("WHEN delete user")
    class WhenDeleteUser {

        @Test
        public void WHEN_user_exists_SHOULD_return_204() throws Exception {

        }

        @Test
        public void WHEN_user_not_found_SHOULD_return_404() throws Exception {

        }
    }

    @Nested
    @DisplayName("WHEN update user")
    class WhenUpdateUser {

        @Test
        public void WHEN_input_valid_SHOULD_return_updated_user() throws Exception {

        }

        @Test
        public void WHEN_input_invalid_SHOULD_return_400() throws Exception {

        }

        @Test
        public void WHEN_user_not_found_SHOULD_return_404() throws Exception {

        }
    }

    @Nested
    @DisplayName("WHEN update user email")
    class WhenUpdateUserEmail {

        @Test
        public void WHEN_email_valid_SHOULD_return_updated_user() throws Exception {

        }

        @Test
        public void WHEN_email_invalid_SHOULD_return_400() throws Exception {

        }

        @Test
        public void WHEN_user_not_found_SHOULD_return_404() throws Exception {

        }
    }

    @Nested
    @DisplayName("WHEN find user by id")
    class WhenFindUserById {

        @Test
        public void WHEN_user_exists_SHOULD_return_user() throws Exception {

        }

        @Test
        public void WHEN_user_not_found_SHOULD_return_404() throws Exception {

        }
    }

    @Nested
    @DisplayName("WHEN find all users")
    class WhenFindAllUsers {

        @Test
        public void WHEN_users_exists_SHOULD_return_users() throws Exception {

        }

        @Test
        public void WHEN_no_users_found_SHOULD_return_empty_collection() throws Exception {

        }
    }
}