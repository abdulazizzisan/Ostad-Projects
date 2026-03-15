package dev.zisan.b3_test.entity;

import dev.zisan.b3_test.model.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String email;
    private String username;
    private String password;

    public UserResponse toResponse(){
        UserResponse response = new UserResponse();
        response.setId(this.id);
        response.setEmail(this.email);
        response.setUsername(this.username);
        return response;
    }
}
