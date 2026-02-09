package dev.zisan.repository;

import dev.zisan.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

//    @BeforeEach // runs before each test method.
//    @BeforeAll // runs once before all test methods.
//    @AfterEach // runs after each test method.
//    @AfterAll // runs once after all test methods.


    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Test
    void addProduct() {
        assertNotNull(productRepository);

        Product product = Product.builder()
                .name("Test Product")
                .description("This is a test product")
                .price(new java.math.BigDecimal("19.99"))
                .quantity(10)
                .category("Test Category")
                .build();

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct);
        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10, savedProduct.getQuantity());
    }

    @Test
    void getAllProductsShouldReturnListOfSizeZero(){
        assertNotNull(resourcePatternResolver);

        List<Product> products = productRepository.findAll();

        assertEquals(0, products.size());

    }
}