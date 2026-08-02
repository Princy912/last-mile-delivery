package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "failed_deliveries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailedDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private DeliveryOrder deliveryOrder;

    @Column(nullable = false)
    private String reason;

    private String notes;

    private LocalDateTime attemptedTime;
}
