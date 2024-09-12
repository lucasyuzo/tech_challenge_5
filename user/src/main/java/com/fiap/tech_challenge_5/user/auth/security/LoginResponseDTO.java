package com.fiap.tech_challenge_5.user.auth.security;

import com.fiap.tech_challenge_5.user.user.User;

public record LoginResponseDTO(
        User user,
        String token
) { }
