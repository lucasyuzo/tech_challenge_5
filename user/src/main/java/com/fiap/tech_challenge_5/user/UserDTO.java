package com.fiap.tech_challenge_5.user;

public record UserDTO(
        String firstName,
        String lastName,
        String email,
        String login,
        String password
) { }
