package dev.zisan.b3_employee_directory.repository;

import dev.zisan.b3_employee_directory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}

// Controller -> Service -> Repository
