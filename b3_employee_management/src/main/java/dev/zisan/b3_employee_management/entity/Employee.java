package dev.zisan.b3_employee_management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.zisan.b3_employee_management.service.EmployeeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Please enter a valid email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Enumerated(EnumType.STRING)
    private Department department;

    private Integer salary;

    private Integer phoneBill;

    private Integer grossSalary;

    @PrePersist // runs before the entity is persisted/saved to the database
    public void setGrossSalary() {
        this.grossSalary = this.salary + this.phoneBill;
        log.info("Gross salary calculated for employee {}: {}", this.name, this.grossSalary);
    }

    @PostPersist
    public void makeFieldsNull() {
        this.email = null;
        this.department = null;
        this.salary = null;
        this.phoneBill = null;
        this.grossSalary = null;
        log.info("Sensitive fields set to null for employee {} after saving to database", this.name);
    }
}

