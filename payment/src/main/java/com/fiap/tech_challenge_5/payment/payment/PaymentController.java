package com.fiap.tech_challenge_5.payment.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody PaymentDTO payment) {
        Payment createdPayment = paymentService.create(payment);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("read-all")
    public ResponseEntity<List<Payment>> readAll() {
        List<Payment> paymentList = paymentService.readAll();
        return ResponseEntity.ok(paymentList);
    }

    @GetMapping("read-by-id/{id}")
    public ResponseEntity<Payment> readById(@PathVariable UUID id) {
        Payment payment = paymentService.readById(id);
        return ResponseEntity.ok(payment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        paymentService.deleteById(id);
        return ResponseEntity.ok("Deleted payment with id " + id);
    }

}
