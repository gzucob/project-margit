package com.gzucob.projectmargit.product.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price, Integer quantity, Instant productAddAt) {
}
