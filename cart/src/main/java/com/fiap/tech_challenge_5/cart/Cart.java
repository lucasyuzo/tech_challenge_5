package com.fiap.tech_challenge_5.cart;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "cart_table")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items;

    public Cart() { }

    public Cart(UUID id, Integer userId, List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Integer productId) {
        items.forEach(cartItem -> {
            if (cartItem.getProductId().equals(productId)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            }
        });
    }

    public void removeItem(Integer productId) {
        AtomicReference<Boolean> toRemove = new AtomicReference<>(false);
        items.forEach(cartItem -> {
            if (cartItem.getProductId().equals(productId)) {
                if (cartItem.getQuantity() == 1) {
                    toRemove.set(true);
                    return;
                }
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            }
        });
        if (toRemove.get()) {
            items.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        }
    }

    public void removeAllItems() {
        items.clear();
    }

    public Double getTotalPrice() {
        return this.items.stream().reduce(0.0, (subtotal, item) -> {
            subtotal += item.getPrice() * item.getQuantity();
            return subtotal;
        }, Double::sum);
    }

}
