package dev.zisan.b2_configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final SSLCommerzeService sslCommerzeService;

    @Value("${google.genai.api_key}")
    String geminiApiKey;


    @GetMapping("/gemini/apiKey")
    public ResponseEntity<String> getGeminiApiKey(){
        return ResponseEntity.ok(geminiApiKey);
    }

    @GetMapping("/sslcommerze")
    public ResponseEntity<Map<String, Object>> getSSLCommerzeConfig() {
        return ResponseEntity.ok(sslCommerzeService.getSSLCommerzeConfig());
    }



    public ConfigController(SSLCommerzeService sslCommerzeService) {
        this.sslCommerzeService = sslCommerzeService;
    }
}
