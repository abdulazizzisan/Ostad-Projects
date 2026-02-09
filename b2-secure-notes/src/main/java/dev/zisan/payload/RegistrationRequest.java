package dev.zisan.payload;

import dev.zisan.entity.User;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
