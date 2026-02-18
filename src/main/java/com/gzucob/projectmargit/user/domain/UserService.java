package com.gzucob.projectmargit.user.domain;

import com.gzucob.projectmargit.user.dto.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser (UserRequest userRequest) {
        return userRepository.save(new User(userRequest));
    }
}
