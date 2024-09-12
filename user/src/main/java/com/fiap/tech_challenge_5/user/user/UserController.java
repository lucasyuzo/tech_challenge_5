package com.fiap.tech_challenge_5.user.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.create(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<User>> readAll() {
        List<User> users = userService.readAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<User> readById(@PathVariable("id") UUID id) {
        User user = userService.readById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        User user = userService.update(id, userDTO);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Deleted user with id " + id);
    }

}
