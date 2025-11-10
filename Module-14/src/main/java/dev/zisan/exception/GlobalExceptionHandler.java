package dev.zisan.exception;

import dev.zisan.ApiResponse;
import dev.zisan.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PhoneNumberNotMatchingException.class)
    public ResponseEntity<ApiResponse<?>> phoneNumberMismatchExceptionHandler(PhoneNumberNotMatchingException ex){
        ApiResponse<?> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.toString(),
                400,
                ex.getMessage(),
                false,
                null
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> notFoundException(NotFoundException ex){
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND.toString(),
                404,
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> runtimeExceptionHandler(Exception ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
