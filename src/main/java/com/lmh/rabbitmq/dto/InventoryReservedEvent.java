package com.lmh.rabbitmq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryReservedEvent {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime timestamp;
}
