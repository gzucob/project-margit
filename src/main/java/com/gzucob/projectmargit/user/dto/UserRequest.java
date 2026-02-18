package com.gzucob.projectmargit.user.dto;

public record UserRequest(String userFirstName, String userLastName, String userEmail, String passwordHash ) {
}
