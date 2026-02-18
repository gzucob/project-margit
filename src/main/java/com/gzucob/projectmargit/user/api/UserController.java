package com.gzucob.projectmargit.user.api;

import com.gzucob.projectmargit.user.domain.User;
import com.gzucob.projectmargit.user.domain.UserService;
import com.gzucob.projectmargit.user.dto.UserRequest;
import com.gzucob.projectmargit.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
