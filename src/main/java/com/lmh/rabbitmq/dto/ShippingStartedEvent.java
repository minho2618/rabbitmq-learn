package com.lmh.rabbitmq.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingStartedEvent {
    private Long orderId;
    private String shippingAddress;
    private String trackingNumber;
    private LocalDateTime timestamp;
}
