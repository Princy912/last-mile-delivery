package com.example.backend.dto;

import com.example.backend.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderDTO {

    private Long id;

    private Long customerId;

    private String customerName;

    private Long deliveryAgentId;

    private String deliveryAgentName;

    private String trackingNumber;

    private String pickupAddress;

    private String deliveryAddress;

    private OrderStatus status;

    private String priority;

    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime actualDeliveryTime;

    private LocalDateTime createdAt;
}