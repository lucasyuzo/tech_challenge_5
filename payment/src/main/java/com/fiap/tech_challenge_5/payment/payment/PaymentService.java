package com.fiap.tech_challenge_5.payment.payment;

import com.fiap.tech_challenge_5.payment.order.OrderRepository;
import com.fiap.tech_challenge_5.payment.order.OrderService;
import com.fiap.tech_challenge_5.payment.order.OrderStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderService orderService
    ) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
    }

    public Payment create(PaymentDTO paymentDTO) {

        Payment payment = new Payment();
        payment.setId(UUID.randomUUID());
        payment.setMethod(paymentDTO.method());
        payment.setOrderId(paymentDTO.orderId());
        payment.setCardName(paymentDTO.cardName());
        payment.setCardNumber(paymentDTO.cardNumber());
        payment.setCreationDateTime(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        orderService.updateOrderPaymentId(payment.getOrderId(), payment.getId());
        orderService.updateOrderStatus(payment.getOrderId(), OrderStatus.IN_PREPARATION);

        return payment;
    }

    public List<Payment> readAll() {
        return paymentRepository.findAll();
    }

    public Payment readById(UUID id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public void deleteById(UUID id) {
        paymentRepository.deleteById(id);
    }

}
