package dev.zisan.b3_employee_directory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee { // table name will be employee by default, but can be changed with @Table(name = "employee")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "emp_name", length = 100)
    private String name; // varchar(20)

    @Enumerated(EnumType.STRING)
    private Department department;

    private String email;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private BigDecimal salary;

}
