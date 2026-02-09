package com.ostad.first_application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    @GetMapping("/welcome-api")
    public String welcomeApi(){
        return "This is our first API. Welcome to Spring Boot!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping
    public String greet() {
        return "Greetings from Spring Boot!";
    }

}
