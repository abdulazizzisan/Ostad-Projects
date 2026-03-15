package dev.zisan.b3_test.repo;

import dev.zisan.b3_test.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.SequencedCollection;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveMethodShouldReturnObjectWithId(){
        User user = new User(null, "email@gmail.com", "username", "password");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    void getUserByIdShouldReturnUserIfExists(){
        Optional<User> optionalUser = userRepository.findById(1);

        assertFalse(optionalUser.isPresent());
    }

    @Test
    void getAllUserShouldReturnListOfUsers(){

        List<User> users = userRepository.findAll();

        assertFalse(users.isEmpty());
    }

    @BeforeEach
    void setUp(){
        User user1 = new User(null, "email@gmail.com", "username", "password");
        User user2 = new User(null, "email@gmail.com", "username", "password");
        User user3 = new User(null, "email@gmail.com", "username", "password");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @AfterEach
    void cleanUp(){
        userRepository.deleteAll();
    }
    
    // @BeforeEach
    // @AfterEach
    // @BeforeAll
    // @AfterAll

}