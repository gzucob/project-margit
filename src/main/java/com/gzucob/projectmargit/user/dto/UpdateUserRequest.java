package com.gzucob.projectmargit.user.dto;

public record UpdateUserRequest(String userFirstName,
                                String userLastName,
                                String userEmail) {
}
