package dev.zisan.b3_employee_management.service;

import dev.zisan.b3_employee_management.entity.Employee;
import dev.zisan.b3_employee_management.exception.BadRequestException;
import dev.zisan.b3_employee_management.exception.NotFoundException;
import dev.zisan.b3_employee_management.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees() {
        var employees = repository.findAll();
        log.info("{} employees fetched", employees.size());
        return employees;
    }

    public Employee getEmployeeById(Integer id) {
        var employee = repository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found with id: " + id));
        log.info("Employees fetched successfully with id: {}", id);
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getSalary() <= 0){
            throw new BadRequestException("Salary must be greater than zero");
        }
        return repository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        return repository.save(existingEmployee);
    }

    public void deleteEmployee(Integer id) {
        Employee existingEmployee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));

        log.warn("Deleting employee with id: {} and name: {}", existingEmployee.getId(), existingEmployee.getName());
        repository.delete(existingEmployee);
    }

}
