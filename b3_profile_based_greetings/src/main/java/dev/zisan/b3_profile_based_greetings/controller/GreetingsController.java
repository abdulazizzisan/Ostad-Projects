package dev.zisan.b3_profile_based_greetings.controller;

import dev.zisan.b3_profile_based_greetings.payload.GreetingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greet")
@RequiredArgsConstructor
public class GreetingsController {
//    @Value("${greetings.message}")
//    String greetingMessage;

    private final GreetingsResponse greetingsResponse;
//
//    @GetMapping("/from/properties")
//    public ResponseEntity<GreetingsResponse> greet() {
//        return ResponseEntity.ok(new GreetingsResponse("Hello ".concat(greetingMessage)));
//    }

    @GetMapping("/from/bean")
    public ResponseEntity<GreetingsResponse> greetFromBean() {
        return ResponseEntity.ok(greetingsResponse);
    }
}
