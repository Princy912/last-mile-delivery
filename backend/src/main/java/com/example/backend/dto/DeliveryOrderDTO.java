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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long customerId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String customerName;

    @NotNull
    @Schema(example = "0")
    private Long deliveryAgentId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String deliveryAgentName;

    @NotBlank
    @Schema(example = "string")
    private String trackingNumber;

    @NotBlank
    @Schema(example = "string")
    private String pickupAddress;

    @NotBlank
    @Schema(example = "string")
    private String deliveryAddress;

    @NotNull
    @Schema(example = "PLACED")
    private OrderStatus status;

    @NotBlank
    @Schema(example = "string")
    private String priority;

    @Future(message = "Estimated delivery date must be in the future.")
    @Schema(example = "2026-01-01T10:00:00")
    private LocalDateTime estimatedDeliveryTime;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime actualDeliveryTime;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime createdAt;
}