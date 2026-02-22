package dev.zisan.b3_m13_student_database_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Min(value = 1, message = "Minimum value for level is 1")
    @Max(value = 12, message = "Maximum value for level is 12")
    private Integer level; // 1 to 12

    @Min(value = 1, message = "Roll number starts from 1")
    private Integer rollNo;

    @Min(value = 1, message = "Roll number starts from 1")
    private Integer age;

    @Pattern(regexp = "^\\+8801[3-9]\\d{8}$") // ^\+8801[3-9]\d{8}$ \n, \t \\
    private String emergencyContact; // +8801754399775 // regular expression

    @Enumerated(EnumType.STRING)
    Shift shift;

    // Define Another class for Student Address
    // Map the Class with @ManyToMany
    // Address Entity -> City, Country, District
}
