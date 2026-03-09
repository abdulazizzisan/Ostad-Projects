package dev.zisan.b3_secure_note.controller;

import dev.zisan.b3_secure_note.entity.User;
import dev.zisan.b3_secure_note.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        var msg = userService.registerUser(user);
        return Map.of("msg", msg);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
