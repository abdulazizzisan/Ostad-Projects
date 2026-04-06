package dev.zisan.b3_profile_based_greetings.configuration;

import dev.zisan.b3_profile_based_greetings.payload.GreetingsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingsConfig {

    @Value("${spring.profiles.active}")
    String profile;

    @Bean
    public GreetingsResponse greetingMessageDev() {
        return new GreetingsResponse("Currently active profile is: ".concat(profile));
    }

//    @Profile({"dev", "hello"})
//    @Bean
//    public GreetingsResponse greetingMessageDev() {
//        return new GreetingsResponse("Greetings from bean for dev/qa env");
//    }

//    @Profile("prod")
//    @Bean
//    public GreetingsResponse greetingMessageProd() {
//        return new GreetingsResponse("Greetings from bean for prod env");
//    }
}
