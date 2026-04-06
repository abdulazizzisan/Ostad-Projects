package dev.zisan.b3_profile_based_greetings.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GreetingsResponse {
    private String message;
}
