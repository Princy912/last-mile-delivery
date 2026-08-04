package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.enums.RouteStopStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStopDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long routeId;

    @NotNull
    @Schema(example = "0")
    private Long deliveryOrderId;

    @NotNull
    @Schema(example = "0")
    private Integer sequence;

    @NotNull
    @Schema(example = "PENDING")
    private RouteStopStatus status;

    @Schema(example = "2026-01-01T10:00:00")
    private LocalDateTime estimatedArrivalTime;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime actualArrivalTime;
}
