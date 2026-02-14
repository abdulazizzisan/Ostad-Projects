package dev.zisan.b2_configurations;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Profile("dev")
public class TestController {

    @GetMapping("/test-api")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("test api.");
    }

}

// External API Call,
