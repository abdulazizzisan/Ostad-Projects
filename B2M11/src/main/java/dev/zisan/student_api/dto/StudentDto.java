package dev.zisan.student_api.dto;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class StudentDto {
    String name;

    String email;

    String phoneNumber;

    Integer rollNo;

    Integer level;

    Double gpa;
}
