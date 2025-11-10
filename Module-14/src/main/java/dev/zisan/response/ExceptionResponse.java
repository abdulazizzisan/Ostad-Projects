package dev.zisan.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String status;
    private int statusCode;
    private String message;
}
