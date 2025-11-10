package dev.zisan.exception;

public class PhoneNumberNotMatchingException extends RuntimeException {

    public PhoneNumberNotMatchingException(String message){
        super(message);
    }

    // enable from configuration file -> static method
}
