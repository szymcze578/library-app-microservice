package org.szymon.user.WebApi.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.szymon.user.Domain.Model.User;
import org.szymon.user.Infrastucture.Services.AuthService;
import org.szymon.user.Security.Jwt.JwtTokenProvider;
import org.szymon.user.WebApi.DataTransferObjects.LoginRequest;
import org.szymon.user.WebApi.DataTransferObjects.RegisterRequest;


@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @GetMapping("/checkUnauthorized")
    public ResponseEntity<String> checkUnauthorized() {
        return ResponseEntity.ok("Unauthorized endpoint accessed!");
    }

    @GetMapping("/checkAuthorizedUser")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> checkAuthorizedUser() {
        return ResponseEntity.ok("Authorized user with ROLE_USER have access!");
    }

    @GetMapping("/checkAuthorizedUserAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> checkAuthorizedAdmin() {
        return ResponseEntity.ok("Authorized admin with ROLE_ADMIN have access!");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
    {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenProvider.generateToken(userDetails);

            return ResponseEntity.ok(jwt);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            logger.info("Registration attempt for username: {}", registerRequest.username());

            User newUser = authService.registerUser(
                    registerRequest.username(),
                    registerRequest.password(),
                    registerRequest.email()
            );

            logger.info("User registered successfully: {}", registerRequest.username());

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser.getId());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
