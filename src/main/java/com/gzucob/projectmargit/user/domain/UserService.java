package com.gzucob.projectmargit.user.domain;

import com.gzucob.projectmargit.user.dto.UserRequest;
import com.gzucob.projectmargit.user.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

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
                        u.getPasswordHash()
                ))
                .toList();
    }
}
