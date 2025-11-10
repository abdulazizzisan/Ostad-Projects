package dev.zisan.controller;

import dev.zisan.ApiResponse;
import dev.zisan.User;
import dev.zisan.UserLoginDTO;
import dev.zisan.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> registerNewUser(@RequestBody @Valid User user){
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDTO> login(@RequestBody UserLoginDTO loginDTO){
        return ResponseEntity.ok(userService.login(loginDTO));
    }

}
