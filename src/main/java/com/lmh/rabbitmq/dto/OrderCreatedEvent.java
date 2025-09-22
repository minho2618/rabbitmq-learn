package com.lmh.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private String customerEmail;
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime timestamp;
}
