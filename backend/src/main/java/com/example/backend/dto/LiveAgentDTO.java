package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveAgentDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "Alex Agent")
    private String agentName;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "37.7749000")
    private BigDecimal latitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "-122.4194000")
    private BigDecimal longitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "BIKE")
    private String vehicleType;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "AVAILABLE")
    private String currentStatus;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime lastUpdated;
}
