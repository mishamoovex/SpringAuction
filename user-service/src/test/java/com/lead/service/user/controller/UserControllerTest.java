package com.lead.service.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lead.service.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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

        }

        @Test
        public void WHEN_request_invalid_SHOULD_return_400() throws Exception {

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