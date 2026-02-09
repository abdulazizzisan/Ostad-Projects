package dev.zisan.controller;

import dev.zisan.entity.User;
import dev.zisan.payload.RegistrationRequest;
import dev.zisan.payload.RegistrationResponse;
import dev.zisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(RegistrationResponse.builder()
                            .username(request.getUsername())
                            .message("Username already exists")
                            .build());
        }

        var user = userRepository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .roles(Set.of("USER"))
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RegistrationResponse.builder()
                        .username(user.getUsername())
                        .message("User registered successfully")
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<RegistrationResponse> login(@RequestBody RegistrationRequest request) {
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> user.getPassword().equals(request.getPassword()))
                .map(user -> ResponseEntity.ok(
                        RegistrationResponse.builder()
                                .username(user.getUsername())
                                .message("Login successful")
                                .build()))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(RegistrationResponse.builder()
                                .username(request.getUsername())
                                .message("Invalid credentials")
                                .build()));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
