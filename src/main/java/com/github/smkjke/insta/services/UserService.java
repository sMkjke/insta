package com.github.smkjke.insta.services;

import com.github.smkjke.insta.entity.User;
import com.github.smkjke.insta.entity.enums.ERole;
import com.github.smkjke.insta.exceptions.UserExistException;
import com.github.smkjke.insta.payload.request.SignupRequest;
import com.github.smkjke.insta.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastName(userIn.getLastname());
        user.setUserName(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoleSet().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving user {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registation {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check" +
                    "credentials");
        }
    }

}
