package dev.zisan.service;

import dev.zisan.entity.Product;
import dev.zisan.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(Integer id) {
        var product = productRepository.findById(id)
                .orElse(null);
        return product;
    }

    public Product addProduct(Product product) {
        Product p = productRepository.save(product);
        log.info("Service: Product {} has been added", p);
        return p;
    }
}
