package com.fiap.tech_challenge_5.cart;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final AuthRestClientGateway authRestClientGateway;

    public CartController(
            CartService service,
            AuthRestClientGateway authRestClientGateway
    ) {
        this.service = service;
        this.authRestClientGateway = authRestClientGateway;
    }

    @PostMapping
    public ResponseEntity<Cart> create(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody Cart cart
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart createdCart = service.create(cart);
            return ResponseEntity.ok(createdCart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Cart>> readAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            List<Cart> carts = service.readAll();
            return ResponseEntity.ok(carts);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Cart> readById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart cart = service.readById(id);
            return ResponseEntity.ok(cart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/add-new-item/{cartId}")
    public ResponseEntity<Cart> addNewItem(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody ItemDTO itemDTO,
            @PathVariable UUID cartId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart updatedCart = service.addNewItem(itemDTO, cartId);
            return ResponseEntity.ok(updatedCart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/add-item-quantity/{productId}/{cartId}")
    public ResponseEntity<Cart> addItemQuantity(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID productId,
            @PathVariable UUID cartId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart updatedCart = service.addItemQuantity(productId, cartId);
            return ResponseEntity.ok(updatedCart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/remove-item-quantity/{productId}/{cartId}")
    public ResponseEntity<Cart> removeItem(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID productId,
            @PathVariable UUID cartId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart updatedCart = service.removeItem(productId, cartId);
            return ResponseEntity.ok(updatedCart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/remove-all/{cartId}")
    public ResponseEntity<Cart> removeAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID cartId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Cart updatedCart = service.removeAllItems(cartId);
            return ResponseEntity.ok(updatedCart);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
