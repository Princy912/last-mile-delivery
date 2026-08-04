package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.backend.enums.TrackingStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTrackingDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long deliveryOrderId;

    @NotNull
    @Schema(example = "0")
    private Long deliveryAgentId;

    @NotNull
    @Schema(example = "0")
    private BigDecimal latitude;

    @NotNull
    @Schema(example = "0")
    private BigDecimal longitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime updatedTime;

    @Schema(example = "TRANSIT")
    private TrackingStatus status;
}
