package org.szymon.user.Infrastucture.Services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.szymon.user.Domain.Model.Role;
import org.szymon.user.Domain.Model.RoleType;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.Domain.Repositories.RoleRepository;
import org.szymon.user.Domain.Repositories.UserRepository;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public User registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            logger.warn("Registration failed: Username already exists: {}", username);
            throw new IllegalArgumentException("Username is already taken");
        }

        Role role = roleRepository.findByType(RoleType.USER)
                .orElseThrow(() -> new IllegalArgumentException("Role type not found"));

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setActive(true);
        newUser.setRole(role);

        User savedUser = userRepository.save(newUser);
        logger.info("New user registered successfully: {}", username);

        return savedUser;
    }
}
