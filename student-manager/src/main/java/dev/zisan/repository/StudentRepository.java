package dev.zisan.repository;

import dev.zisan.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    @Query("SELECT s FROM Student s WHERE s.address LIKE %:keyword%")
    List<Student> findStudentsByAddressContaining(@Param("keyword") String keyword);

    Optional<Student> findByClassRoll(int classRoll);

}
