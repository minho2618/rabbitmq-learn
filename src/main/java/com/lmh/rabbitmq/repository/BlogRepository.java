package com.lmh.rabbitmq.repository;

import com.lmh.rabbitmq.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Order, Long> {
}
