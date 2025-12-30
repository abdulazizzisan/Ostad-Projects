package dev.zisan.student_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    Integer id;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "")
    String name;

    @Email(message = "Email should be valid")
    String email;

    //    @Pattern(regexp = "^\+8801[3-9]\d{8}$\n")
    String phoneNumber;

    Integer rollNo;

    Integer level;

    @Max(value = 5, message = "GPA cannot be more than 5.00")
    @Min(value = 0, message = "GPA cannot be less than 0.00")
    Double gpa;
}
