package com.gzucob.projectmargit.user.domain;

import com.gzucob.projectmargit.user.dto.CreateUserRequest;
import com.gzucob.projectmargit.user.dto.UpdatePasswordRequest;
import com.gzucob.projectmargit.user.dto.UpdateUserRequest;
import com.gzucob.projectmargit.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser (CreateUserRequest createUserRequest) {
        String encodedPassword = passwordEncoder.encode(createUserRequest.passwordHash());
        User user = new User(
                createUserRequest.userFirstName(),
                createUserRequest.userLastName(),
                createUserRequest.userEmail(),
                encodedPassword
        );
        return userRepository.save(user);
    }

    public List<UserResponse> findAllUsers () {
        return userRepository.findAll().stream()
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getUserFullName(),
                        u.getUserEmail(),
                        u.getPasswordHash(),
                        ""
                ))
                .toList();
    }

    public UserResponse deleteById (UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return new UserResponse(
                user.getId(),
                user.getUserFullName(),
                user.getUserEmail(),
                user.getPasswordHash(),
                "User deleted successfully"
        );
    }

    public User updateUserById(UUID id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.updateUser(
                updateUserRequest.userFirstName(),
                updateUserRequest.userLastName(),
                updateUserRequest.userEmail()
        );
        return userRepository.save(user);
    }

    public User updatePasswordById (UUID id, UpdatePasswordRequest updatePasswordRequest) {
        String encodedPassword = passwordEncoder.encode(updatePasswordRequest.passwordHash());
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.updatePassword(
                encodedPassword
        );
        return userRepository.save(user);
    }
}
