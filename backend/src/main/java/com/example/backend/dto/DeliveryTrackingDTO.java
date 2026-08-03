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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "10")
    private Long deliveryOrderId;

    @NotNull
    @Schema(example = "5")
    private Long deliveryAgentId;

    @NotNull
    @Schema(example = "37.7749000")
    private BigDecimal latitude;

    @NotNull
    @Schema(example = "-122.4194000")
    private BigDecimal longitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime updatedTime;

    @Schema(example = "TRANSIT")
    private TrackingStatus status;
}
