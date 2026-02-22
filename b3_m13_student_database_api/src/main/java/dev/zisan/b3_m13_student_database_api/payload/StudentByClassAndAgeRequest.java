package dev.zisan.b3_m13_student_database_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentByClassAndAgeRequest {
    private Integer level;
    private Integer age;
}
