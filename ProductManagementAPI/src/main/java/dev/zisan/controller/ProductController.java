package dev.zisan.controller;

import ch.qos.logback.classic.Logger;
import dev.zisan.entity.Product;
import dev.zisan.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    Logger logger = (Logger) LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @PostMapping("/add")
    public Product addProduct(@RequestBody @Valid Product product) {

        logger.info("Adding product {}", product);
        return productService.addProduct(product);
    }
}
