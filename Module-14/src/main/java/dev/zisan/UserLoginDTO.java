package dev.zisan;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserLoginDTO {
    @Email(message = "You must enter a valid email.")
    private String email;
    private String password;
}
