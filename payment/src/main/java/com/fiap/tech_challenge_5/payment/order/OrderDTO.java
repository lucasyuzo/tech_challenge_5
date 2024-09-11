package com.fiap.tech_challenge_5.payment.order;

import java.util.List;
import java.util.UUID;

public record OrderDTO(
    List<ItemDTO> itemsDTO,
    UUID userId
) { }
