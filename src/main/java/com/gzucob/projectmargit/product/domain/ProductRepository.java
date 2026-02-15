package com.gzucob.projectmargit.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository <Product, UUID> {
    boolean existsByName(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
}
