package com.fiap.tech_challenge_5.payment.order;

import java.util.UUID;

public record ItemDTO(
    UUID productId,
    Integer quantity,
    Double price
) { }
