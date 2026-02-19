package com.gzucob.projectmargit.product.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateProductRequest(
        @Size(min = 1, max = 255, message = "Name cannot be empty")
        String name,

        @Positive(message = "Price must be positive")
        BigDecimal price,

        @PositiveOrZero(message = "Quantity cannot be negative")
        Integer quantity
) {}
