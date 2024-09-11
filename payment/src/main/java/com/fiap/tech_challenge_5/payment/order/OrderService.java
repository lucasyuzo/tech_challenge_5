package com.fiap.tech_challenge_5.payment.order;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(OrderDTO orderDTO) {

        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setUserId(orderDTO.userId());
        order.setPaymentId(null);

        List<Item> items = orderDTO.itemsDTO().stream().map(itemDTO -> {
            Item item = new Item();
            item.setId(UUID.randomUUID());
            item.setProductId(itemDTO.productId());
            item.setQuantity(itemDTO.quantity());
            item.setPrice(itemDTO.price());
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);

        return orderRepository.save(order);
    }

    public List<Order> readAll() {
        return orderRepository.findAll();
    }

    public Order readById(UUID id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Order updateOrderStatus(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Order updateOrderPaymentId(UUID id, UUID paymentId) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        order.setPaymentId(paymentId);
        return orderRepository.save(order);
    }

    public void deleteById(UUID id) {
        orderRepository.deleteById(id);
    }

}
