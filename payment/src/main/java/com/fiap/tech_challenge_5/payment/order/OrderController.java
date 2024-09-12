package com.fiap.tech_challenge_5.payment.order;

import com.fiap.tech_challenge_5.payment.AuthRestClientGateway;
import com.fiap.tech_challenge_5.payment.payment.Payment;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final AuthRestClientGateway authRestClientGateway;

    public OrderController(
            OrderService orderService,
            AuthRestClientGateway authRestClientGateway
    ) {
        this.orderService = orderService;
        this.authRestClientGateway = authRestClientGateway;
    }

    @PostMapping
    public ResponseEntity<Order> create(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody OrderDTO orderDTO
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Order order = orderService.create(orderDTO);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-all")
    public ResponseEntity<List<Order>> readAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            List<Order> orders = orderService.readAll();
            return ResponseEntity.ok(orders);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Order> readById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Order order = orderService.readById(id);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/update-order-status/{id}/{status}")
    public ResponseEntity<Order> updateOrderStatus(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id,
            @PathVariable OrderStatus status
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Order order = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/update-order-payment-id/{id}/{paymentId}")
    public ResponseEntity<Order> updateOrderPaymentId(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id,
            @PathVariable UUID paymentId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Order order = orderService.updateOrderPaymentId(id, paymentId);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            orderService.deleteById(id);
            return ResponseEntity.ok("Deleted order with id " + id);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
