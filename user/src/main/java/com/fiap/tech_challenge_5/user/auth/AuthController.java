package com.fiap.tech_challenge_5.user.auth;

import com.fiap.tech_challenge_5.user.auth.security.LoginResponseDTO;
import com.fiap.tech_challenge_5.user.user.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        User user = authService.readByLogin(loginDTO.login());
        if (authService.validateCredentials(loginDTO, user)) {
            String token = authService.generateToken(user.getId());
            LoginResponseDTO response = new LoginResponseDTO(user, token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/validate-token/{userId}/{token}")
    public ResponseEntity<Void> validateToken(@PathVariable UUID userId, @PathVariable String token) {
        var authentication = authService.validateToken(token, userId);
        if (authentication) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
