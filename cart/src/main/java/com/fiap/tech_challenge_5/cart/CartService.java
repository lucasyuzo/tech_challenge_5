package com.fiap.tech_challenge_5.cart;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final CartRepository repository;

    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    public Cart create(Cart cart) {
        return repository.save(cart);
    }

    public List<Cart> readAll() {
        return repository.findAll();
    }

    public Cart readById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Cart addNewItem(ItemDTO itemDTO, UUID cartId) {
        Cart cart = repository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        Item item = new Item(UUID.randomUUID(), itemDTO.productId(), 1, itemDTO.price(), cart);
        cart.getItems().add(item);
        return repository.save(cart);
    }

    public Cart addItemQuantity(UUID productId, UUID cartId) {
        Cart cart = repository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        cart.addItem(productId);
        return repository.save(cart);
    }

    public Cart removeItem(UUID productId, UUID cartId) {
        Cart cart = repository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        cart.removeItem(productId);
        return repository.save(cart);
    }

    public Cart removeAllItems(UUID cartId) {
        Cart cart = repository.findById(cartId).orElseThrow(EntityNotFoundException::new);
        cart.removeAllItems();
        return repository.save(cart);
    }

}
