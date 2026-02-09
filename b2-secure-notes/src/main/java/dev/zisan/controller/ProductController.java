package dev.zisan.controller;

import dev.zisan.entity.Product;
import dev.zisan.exception.CustomException;
import dev.zisan.repository.ProductRepository;
import dev.zisan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CustomException("Product not found with id: " + id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("Product not found with id: " + id);
        }
        product.setId(id);
        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            throw new CustomException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productRepository.findByCategory(category));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        return ResponseEntity.ok(productRepository.findByNameContainingIgnoreCase(name));
    }

    @GetMapping("/price/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsByMaxPrice(@PathVariable BigDecimal maxPrice) {
        return ResponseEntity.ok(productRepository.findByPriceLessThanEqual(maxPrice));
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Product>> getProductsByUser(@PathVariable Long userId) {
//        return ResponseEntity.ok(productRepository.findByUserId(userId));
//    }

    @PatchMapping("/{id}/quantity")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException("Product not found with id: " + id));

        product.setQuantity(quantity);
        return ResponseEntity.ok(productRepository.save(product));
    }
}
