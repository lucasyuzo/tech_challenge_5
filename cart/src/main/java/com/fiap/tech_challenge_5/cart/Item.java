package com.fiap.tech_challenge_5.cart;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "item_table")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer productId;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;

    public Item() { }

    public Item(UUID id, Integer productId, Integer quantity, Double price, Cart cart) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.cart = cart;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
