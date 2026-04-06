package dev.zisan.b3_profile_based_greetings.controller;

import dev.zisan.b3_profile_based_greetings.payload.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {



//    @Value("${ostad.test}")
//    String testVal;
//
//    @GetMapping
//    public String test(){
//        return testVal;
//    }

//    @GetMapping("/user")
//    public User testUser() {
//        return new User(
//                "John Doe",
//                "John",
//                "H",
//                "Doe",
//                "+8801234567890",
//                "Dhaka, Bangladesh",
//                25.0f,
//                false,
//                null,
//                "securePassword123"
//        );
//    }

    @GetMapping("/user")
    public User testUserWithBuilder() {
        return User.builder()
                .firstName("John")
                .middleName("H")
                .lastName("Doe")
                .gender("male".toUpperCase())
                .build();
    }
}






