package com.example.backend.dto;

import com.example.backend.enums.OrderStatus;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryOrderDTO {

    private Long id;

    @NotNull
    private Long customerId;

    private String customerName;

    @NotNull
    private Long deliveryAgentId;

    private String deliveryAgentName;

    @NotBlank
    private String trackingNumber;

    @NotBlank
    private String pickupAddress;

    @NotBlank
    private String deliveryAddress;

    @NotNull
    private OrderStatus status;

    @NotBlank
    private String priority;

    @Future(message = "Estimated delivery date must be in the future.")
    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime actualDeliveryTime;

    private LocalDateTime createdAt;
}