package dev.zisan.b3_secure_note.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/ping")
    public Map<String , String> ping(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authorization);
        return Map.of("msg", "pong");
    }

    @GetMapping("/ding")
    public Map<String , String> ding() {
        return Map.of("msg", "dong");
    }

}
