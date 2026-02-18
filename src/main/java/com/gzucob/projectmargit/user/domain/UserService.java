package com.gzucob.projectmargit.user.domain;

import com.gzucob.projectmargit.product.domain.ProductRepository;
import com.gzucob.projectmargit.user.dto.UserRequest;
import com.gzucob.projectmargit.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser (UserRequest userRequest) {
        return userRepository.save(new User(userRequest));
    }

    public List<UserResponse> findAllUsers () {
        return userRepository.findAll().stream()
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getUserFullName(),
                        u.getUserEmail(),
                        u.getPasswordHash(),
                        "User has been created"
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
}
