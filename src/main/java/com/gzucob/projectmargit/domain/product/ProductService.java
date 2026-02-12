package com.gzucob.projectmargit.domain.product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct (ProductRequest productRequest) {
        if (productRequest.price().compareTo(new BigDecimal("0.01")) < 0) {
            throw new IllegalArgumentException("The price cannot be 0 or negative.");
        }
        if (productRepository.existsByName(productRequest.name())) {
            throw new IllegalArgumentException("Product alredy exists");
        }
        return productRepository.save(new Product(productRequest));
    }
}
