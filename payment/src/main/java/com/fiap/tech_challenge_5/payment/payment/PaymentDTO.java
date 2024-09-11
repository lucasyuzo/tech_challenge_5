package com.fiap.tech_challenge_5.payment.payment;

import java.util.UUID;

public record PaymentDTO(
        PaymentMethod method,
        UUID orderId,
        String cardName,
        String cardNumber
) { }
