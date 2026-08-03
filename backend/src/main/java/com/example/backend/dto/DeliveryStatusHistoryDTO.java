package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryStatusHistoryDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "10")
    private Long deliveryOrderId;

    @NotNull
    @Schema(example = "DELIVERED")
    private OrderStatus status;

    @Schema(example = "Updated order status to DELIVERED")
    private String comments;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime updatedTime;
}
