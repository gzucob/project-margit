package com.gzucob.projectmargit.domain.product;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price, Integer quantity) {
}
