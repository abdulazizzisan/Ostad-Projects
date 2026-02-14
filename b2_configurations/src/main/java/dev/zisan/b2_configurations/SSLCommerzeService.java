package dev.zisan.b2_configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SSLCommerzeService {

    @Value("${sslcommerze.store_id}")
    String storeId;

    @Value("${sslcommerze.store_password}")
    String storePassword;

    @Value("${sslcommerze.api.key}")
    String apiKey;

    @Value("${sslcommerze.sandbox}")
    Boolean sandbox;

    @Value("${sslcommerze.api.base_url}")
    String baseUrl;

    public Map<String, Object> getSSLCommerzeConfig() {
        return Map.of(
                "storeId", storeId,
                "storePassword", storePassword,
                "apiKey", apiKey,
                "sandbox", sandbox,
                "baseUrl", baseUrl
        );
    }

}
