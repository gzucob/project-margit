package com.gzucob.projectmargit.user.dto;

import jakarta.validation.constraints.Email;

public record UserRequest(String userFirstName,
                          String userLastName,
                          @Email(message = "Invalid email address") String userEmail,
                          String passwordHash ) {
}
