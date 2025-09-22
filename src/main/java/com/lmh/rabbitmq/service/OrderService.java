package com.lmh.rabbitmq.service;

import com.lmh.rabbitmq.config.RabbitMQConfig;
import com.lmh.rabbitmq.domain.Order;
import com.lmh.rabbitmq.dto.OrderCreatedEvent;
import com.lmh.rabbitmq.repository.OrderRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service // 빈으로 등록
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public Order createOrder(Order order) {
        // 주문 상태를 PENDING으로 설정
        order.setStatus(Order.OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        // 주문 저장
        Order savedOrder = orderRepository.save(order);
        log.info("주문 생성됨: {}", savedOrder.getId());
        
        // 주문 생성 이벤트 발생
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(savedOrder.getId())
                .customerEmail(savedOrder.getCustomerEmail())
                .productId(savedOrder.getProductId())
                .productName(savedOrder.getProductName())
                .quantity(savedOrder.getQuantity())
                .price(savedOrder.getPrice())
                .timestamp(LocalDateTime.now())
                .build();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_CREATED_ROUTING_KEY,
                event
        );

        log.info("주문 생성 이벤트 발행: {}", event);
        return savedOrder;
    }

    @Transactional
    public void updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다: " + orderId));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);

        log.info("주문 상태 업데이트: 주문ID={}, 상태={}", orderId, status);
    }

    public List<Order> getOrdersByCustomer(String customerEmail) {
        return orderRepository.findByCustomerEmailOrderByCreatedAtDesc(customerEmail);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다: " + orderId));
    }
}
