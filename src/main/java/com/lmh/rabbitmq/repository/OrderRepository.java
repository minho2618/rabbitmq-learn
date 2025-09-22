package com.lmh.rabbitmq.repository;

import com.lmh.rabbitmq.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerEmailOrderByCreatedAtDesc(String customerEmail);

    List<Order> findByStatus(Order.OrderStatus status);

}
