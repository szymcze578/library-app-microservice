package org.szymon.user.Infrastucture.Services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.szymon.user.Domain.Model.Role;
import org.szymon.user.Domain.Model.RoleType;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.Domain.Repositories.RoleRepository;
import org.szymon.user.Domain.Repositories.UserRepository;
import org.szymon.user.Security.Jwt.JwtTokenProvider;
import org.szymon.user.Security.Model.AuthResponse;
import org.szymon.user.Security.Model.AuthUserDetails;
import org.szymon.user.WebApi.DataTransferObjects.LoginRequest;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse login(LoginRequest loginRequest){
        logger.info("Login attempt for username: {}", loginRequest.username());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails);
        logger.info("User logged in successfully: {}", userDetails.getUsername());
        return new AuthResponse(
                token,
                "Bearer",
                userDetails.getUser().getId(),
                userDetails.getUsername(),
                userDetails.getUser().getRole().getType().name()
        );
    }

    public User registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            logger.warn("Registration failed: Username already exists: {}", username);
            throw new IllegalArgumentException("Username is already in use!");
        }

        if(userRepository.existsByEmail(email)) {
            logger.warn("Registration failed: Email already exists: {}", email);
            throw new IllegalArgumentException("Email is already in use!");
        }

        Role role = roleRepository.findByType(RoleType.USER)
                .orElseThrow(() -> new IllegalArgumentException("Role type does not exist!"));

        User newUser = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .isActive(true)
                .role(role)
                .build();

        User savedUser = userRepository.save(newUser);
        logger.info("New user registered successfully: {}", username);

        return savedUser;
    }
}
