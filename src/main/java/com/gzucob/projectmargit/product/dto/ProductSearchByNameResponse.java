package com.gzucob.projectmargit.product.dto;

import java.util.UUID;

public record ProductSearchByNameResponse(UUID id, String name, String message) {
}
