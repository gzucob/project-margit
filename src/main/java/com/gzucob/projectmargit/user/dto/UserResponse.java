package com.gzucob.projectmargit.user.dto;

import java.util.UUID;

public record UserResponse(UUID id,
                           String userFullName,
                           String userEmail,
                           String passwordHash,
                           String message) {
}
