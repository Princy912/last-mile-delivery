package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.backend.enums.TrackingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_trackings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private DeliveryOrder deliveryOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    private DeliveryAgent deliveryAgent;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;

    private LocalDateTime updatedTime;

    @Enumerated(EnumType.STRING)
    private TrackingStatus status;  
}
