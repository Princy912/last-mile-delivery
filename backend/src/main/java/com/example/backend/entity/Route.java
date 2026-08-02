package com.example.backend.entity;

import com.example.backend.enums.RouteStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id", nullable = false)
    private DeliveryAgent deliveryAgent;

    @Column(nullable = false)
    private String routeName;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal startLat;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal startLng;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal endLat;

    @Column(nullable = false, precision = 10, scale = 7)
    private BigDecimal endLng;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RouteStatus status;

    private LocalDateTime createdAt;
}
