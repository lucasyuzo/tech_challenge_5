package com.fiap.tech_challenge_5.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody Cart cart) {
        Cart createdCart = service.create(cart);
        return ResponseEntity.ok(createdCart);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Cart>> readAll() {
        List<Cart> carts = service.readAll();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Cart> readById(@PathVariable UUID id) {
        Cart cart = service.readById(id);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/add-new-item/{cartId}")
    public ResponseEntity<Cart> addNewItem(@RequestBody ItemDTO itemDTO, @PathVariable UUID cartId) {
        Cart updatedCart = service.addNewItem(itemDTO, cartId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/add-item-quantity/{productId}/{cartId}")
    public ResponseEntity<Cart> addItemQuantity(@PathVariable UUID productId, @PathVariable UUID cartId) {
        Cart updatedCart = service.addItemQuantity(productId, cartId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/remove-item-quantity/{productId}/{cartId}")
    public ResponseEntity<Cart> removeItem(@PathVariable UUID productId, @PathVariable UUID cartId) {
        Cart updatedCart = service.removeItem(productId, cartId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/remove-all/{cartId}")
    public ResponseEntity<Cart> removeAll(@PathVariable UUID cartId) {
        Cart updatedCart = service.removeAllItems(cartId);
        return ResponseEntity.ok(updatedCart);
    }

}
