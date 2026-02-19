package com.gzucob.projectmargit.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateProductRequest (
        @NotBlank(message = "Product name cannot be empty")
        String name,

        @NotNull(message = "Product price cannot be null")
        @Positive(message = "Product price must be greater than zero")
        BigDecimal price,

        @NotNull(message = "Product quantity cannot be null")
        @PositiveOrZero(message = "Product quantity must be greater than zero")
        Integer quantity

) {}
