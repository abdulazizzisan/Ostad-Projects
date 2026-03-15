package dev.zisan.b3_test.repo;

import dev.zisan.b3_test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
