package com.lmh.rabbitmq.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum OrderStatus {
        PENDING,        // 주문 생성됨
        INVENTORY_RESERVED,  // 재고 확보됨
        PAYMENT_PROCESSED,   // 결제 완료됨
        SHIPPED,        // 배송 시작됨
        DELIVERED,      // 배송 완료됨
        CANCELLED,      // 취소됨
        FAILED          // 실패함
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerEmail='" + customerEmail + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
