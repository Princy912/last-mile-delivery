package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "2")
    private Long customerId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "John Customer")
    private String customerName;

    @NotNull
    @Schema(example = "1")
    private Long deliveryAgentId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "Alex Agent")
    private String deliveryAgentName;

    @NotBlank
    @Schema(example = "TRK-987654321")
    private String trackingNumber;

    @NotBlank
    @Schema(example = "123 Pickup Ln")
    private String pickupAddress;

    @NotBlank
    @Schema(example = "456 Delivery Rd")
    private String deliveryAddress;

    @NotNull
    @Schema(example = "PLACED")
    private OrderStatus status;

    @NotBlank
    @Schema(example = "HIGH")
    private String priority;

    @Future(message = "Estimated delivery date must be in the future.")
    @Schema(example = "2026-08-04T12:00:00")
    private LocalDateTime estimatedDeliveryTime;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime actualDeliveryTime;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime createdAt;
}