package com.fiap.tech_challenge_5.cart;

import java.util.UUID;

public record ItemDTO(
    UUID productId,
    Double price
) { }
