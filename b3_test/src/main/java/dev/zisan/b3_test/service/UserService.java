package dev.zisan.b3_test.service;

import dev.zisan.b3_test.entity.User;
import dev.zisan.b3_test.model.UserRequest;
import dev.zisan.b3_test.model.UserResponse;
import dev.zisan.b3_test.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User createUser(UserRequest request) {
        User entity = new User();
        entity.setEmail(request.getEmail());
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
        return repository.save(entity);
    }

    public UserResponse getUserByID(Integer id) {

        User user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        return response;
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll().stream().map(user -> {
            UserResponse response = new UserResponse();
            response.setId(user.getId());
            response.setEmail(user.getEmail());
            response.setUsername(user.getUsername());
            return response;
        }).toList();
    }
}
