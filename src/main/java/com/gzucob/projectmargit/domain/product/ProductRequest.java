package com.gzucob.projectmargit.domain.product;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
}
