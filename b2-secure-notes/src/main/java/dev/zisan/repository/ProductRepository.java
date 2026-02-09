package dev.zisan.repository;

import dev.zisan.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);
//    List<Product> findByUserId(Long userId);
    List<Product> findByPriceLessThanEqual(BigDecimal price);
    List<Product> findByNameContainingIgnoreCase(String name);
}
