package org.szymon.user.Security.Model;

public record AuthResponse(
        String accessToken,
        String tokenType,
        Long userId,
        String username,
        String role
) {}
