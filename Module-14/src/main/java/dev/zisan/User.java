package dev.zisan;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {

    private Long id;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "address cannot be blank")
    private String address;

    // +880172345
    @NotBlank(message = "phone number cannot be blank")
//    @Pattern(regexp = "^\\+8801[3-9]\\d{8}$\n") //good to know, not a must
    private String phoneNumber;

    @Email(message = "you must provide a valid email")
    @NotBlank(message = "email cannot be blank")
    private String email;

}
