package dev.zisan.service;

import dev.zisan.ApiResponse;
import dev.zisan.User;
import dev.zisan.UserLoginDTO;
import dev.zisan.exception.NotFoundException;
import dev.zisan.exception.PhoneNumberNotMatchingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class UserService {

//    Know this but you can just use lombok for this purpose
//    private final Logger log = LoggerFactory.getLogger(UserService.class);


    public ApiResponse<User> registerUser(User user) {

//        Optional<User> optionalUser = Optional.ofNullable(null);

        if (!user.getPhoneNumber().startsWith("+880")){
            throw new PhoneNumberNotMatchingException("Enter a valid phone number.");
        }
//        User user2 = optionalUser
//                .orElseThrow(() -> new NotFoundException("User not found."));

        // Save the user in the database and then return the persisted user.

        log.info("User saved in the database successfully.");

        ApiResponse<User> response = new ApiResponse<>(
                HttpStatus.OK.toString(),
                200,
                "User saved successfully",
                false,
                user
        );

        return response;
    }

    public UserLoginDTO login(UserLoginDTO loginDTO) {
        return loginDTO;
    }
}
