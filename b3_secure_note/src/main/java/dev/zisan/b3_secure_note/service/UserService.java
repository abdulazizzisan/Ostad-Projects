package dev.zisan.b3_secure_note.service;

import dev.zisan.b3_secure_note.entity.User;
import dev.zisan.b3_secure_note.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public String registerUser(User user){
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        if (user.getId() == null){
            return "User registration failed";
        } else {
            return "User registered successfully with id: " + user.getId();
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
