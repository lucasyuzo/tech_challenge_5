package com.fiap.tech_challenge_5.user.user;

import com.fiap.tech_challenge_5.user.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody UserDTO userDTO
    ) {
        if (authService.validateToken(token, userId)) {
            User user = userService.create(userDTO);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @GetMapping("/read-all")
    public ResponseEntity<List<User>> readAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId
    ) {
        if (authService.validateToken(token, userId)) {
            List<User> users = userService.readAll();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<User> readById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authService.validateToken(token, userId)) {
            User user = userService.readById(id);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id,
            @RequestBody UserDTO userDTO
    ) {
        if (authService.validateToken(token, userId)) {
            User user = userService.update(id, userDTO);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authService.validateToken(token, userId)) {
            userService.deleteById(id);
            return ResponseEntity.ok("Deleted user with id " + id);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
