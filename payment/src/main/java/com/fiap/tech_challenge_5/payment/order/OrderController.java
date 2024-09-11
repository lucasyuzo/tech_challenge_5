package com.fiap.tech_challenge_5.payment.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.create(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Order>> readAll() {
        List<Order> orders = orderService.readAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Order> readById(@PathVariable UUID id) {
        Order order = orderService.readById(id);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/update-order-status/{id}/{status}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable UUID id, @PathVariable OrderStatus status) {
        Order order = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/update-order-payment-id/{id}/{paymentId}")
    public ResponseEntity<Order> updateOrderPaymentId(@PathVariable UUID id, @PathVariable UUID paymentId) {
        Order order = orderService.updateOrderPaymentId(id, paymentId);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        orderService.deleteById(id);
        return ResponseEntity.ok("Deleted order with id " + id);
    }

}
