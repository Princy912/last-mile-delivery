package com.example.backend.entity;

import com.example.backend.enums.PodType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pod_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PODRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private DeliveryOrder deliveryOrder;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PodType podType;

    @Column(nullable = false)
    private String podData;

    @Column(nullable = false)
    private LocalDateTime capturedAt;
}