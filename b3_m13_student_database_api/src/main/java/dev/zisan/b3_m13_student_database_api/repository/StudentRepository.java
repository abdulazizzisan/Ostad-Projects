package dev.zisan.b3_m13_student_database_api.repository;

import dev.zisan.b3_m13_student_database_api.entity.Shift;
import dev.zisan.b3_m13_student_database_api.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByShift(Shift shift); // Derived query

    @Query(value = """
                                SELECT * FROM student
                                WHERE shift = :shift
            """,countQuery = """
                                SELECT COUNT(*) FROM student
                                WHERE shift = :shift
""", nativeQuery = true)
    Page<Student> findByShift(@Param("shift") Shift shift, Pageable pageable); // Derived query

    // JPQL -> Java Persistence Query Language

    @Query("SELECT COUNT(s) FROM Student s WHERE s.level = 10 AND s.age < 15")
    Integer countStudentsFromClass10UnderAge15();

    @Query(value = """
                    SELECT COUNT(*) FROM student
                    WHERE level = 9 AND age < 15
            """, nativeQuery = true)
    Integer countStudentsFromClass9UnderAge15();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.level = :level AND s.age < :age")
    Integer countByLevelAndAgeLessThan(@Param("level") Integer level, @Param("age") Integer age);


}
