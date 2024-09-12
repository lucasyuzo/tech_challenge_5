package com.fiap.tech_challenge_5.payment.payment;

import com.fiap.tech_challenge_5.payment.AuthRestClientGateway;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final AuthRestClientGateway authRestClientGateway;

    public PaymentController(
            PaymentService paymentService,
            AuthRestClientGateway authRestClientGateway
    ) {
        this.paymentService = paymentService;
        this.authRestClientGateway = authRestClientGateway;
    }

    @PostMapping
    public ResponseEntity<Payment> create(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @RequestBody PaymentDTO payment
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Payment createdPayment = paymentService.create(payment);
            return ResponseEntity.ok(createdPayment);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("read-all")
    public ResponseEntity<List<Payment>> readAll(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            List<Payment> paymentList = paymentService.readAll();
            return ResponseEntity.ok(paymentList);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("read-by-id/{id}")
    public ResponseEntity<Payment> readById(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            Payment payment = paymentService.readById(id);
            return ResponseEntity.ok(payment);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @RequestHeader("Access-Token") String token,
            @RequestHeader("User-Id") @Valid UUID userId,
            @PathVariable UUID id
    ) {
        if (authRestClientGateway.validateToken(token, userId)) {
            paymentService.deleteById(id);
            return ResponseEntity.ok("Deleted payment with id " + id);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
