package dev.zisan.employee.repository;

import dev.zisan.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findAllByGender(String gender, Pageable pageable);

    Integer countAllByAddressContainingIgnoreCaseAndAgeLessThan(String address, Integer age);

    @Query("SELECT e FROM Employee e WHERE e.address LIKE %?1% AND e.salary < ?2")
    List<Employee> findByAddressAndSalary(String address, Integer salary);

    void deleteAllBySalaryGreaterThan(Integer salary);

}
