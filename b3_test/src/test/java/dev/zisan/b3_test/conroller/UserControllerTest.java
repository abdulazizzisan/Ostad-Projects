package dev.zisan.b3_test.conroller;

import dev.zisan.b3_test.entity.User;
import dev.zisan.b3_test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Test
    public void createUserApiShouldReturn201CreatedIfSuccessful() throws Exception {

        when(userService.createUser(any()))
                .thenReturn(new User(1, "hello@mail.com", "hello", "password123"));

        String requestBody = """
                 {
                     "email": "hello@gmail.com",
                     "username": "hello",
                     "password": "password123"
                }
                """;

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void createUserApiShouldHaveValidId() throws Exception {

        when(userService.createUser(any()))
                .thenReturn(new User(5, "hello@mail.com", "hello", "password123"));

        String requestBody = """
                 {
                     "email": "hello@gmail.com",
                     "username": "hello",
                     "password": "password123"
                }
                """;

        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.email").isString());
    }

}