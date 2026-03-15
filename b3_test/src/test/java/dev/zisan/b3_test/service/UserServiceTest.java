package dev.zisan.b3_test.service;

import dev.zisan.b3_test.entity.User;
import dev.zisan.b3_test.model.UserRequest;
import dev.zisan.b3_test.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser(){
        UserRequest request = new UserRequest("hello@email.com", "hello", "password");
        User u = new User(1, request.getEmail(), request.getUsername(), "password");
        when(userRepository.save(any(User.class))).thenReturn(u);

        User user = userService.createUser(request);

        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(request.getEmail(), user.getEmail());
        assertEquals(request.getUsername(), user.getUsername());
        assertEquals(request.getPassword(), user.getPassword());
    }

    @Test
    void createUserShouldSavePasswordProperly(){
        UserRequest request = new UserRequest("hello@email.com", "hello", "strongPassword1234");
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> {
                    User user = (User) invocation.getArgument(0);
                    user.setId(1);
                    return user;
                });

        User user = userService.createUser(request);

        assertEquals(request.getPassword(), user.getPassword());
    }

}