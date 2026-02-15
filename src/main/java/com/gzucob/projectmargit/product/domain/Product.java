package com.gzucob.projectmargit.product.domain;

import com.gzucob.projectmargit.product.dto.ProductRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "quantity", unique = true, nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @CreationTimestamp
    @Column(name = "product_add_at", nullable = false, updatable = false)
    private Instant productAddAt;

    public Product(ProductRequest productRequest) {
        validatePrice(productRequest.price());
        validateQuantity(productRequest.quantity());

        this.name = productRequest.name().trim();
        this.price = productRequest.price();
        this.quantity = productRequest.quantity();
    }

    public void validatePrice (BigDecimal price) {
        if (price.compareTo(new BigDecimal("0.01")) < 0) {
            throw new IllegalArgumentException("The price cannot be zero or negative.");
        }
    }

    public void validateQuantity (int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be a zero or negative");
        }
    }
}
