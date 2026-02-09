package dev.zisan.controller;

import dev.zisan.entity.Product;
import dev.zisan.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductController controller;

    @Test
    void createProductShouldReturnCreatedProduct(){

        Product product = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("This is a test product")
                .price(new java.math.BigDecimal("19.99"))
                .quantity(10)
                .category("Test Category")
                .build();

        when(repository.save(product)).thenReturn(product);


        var response = controller.createProduct(product);

        assertInstanceOf(Product.class, response.getBody());

        assertNotNull(product.getId());
    }
}