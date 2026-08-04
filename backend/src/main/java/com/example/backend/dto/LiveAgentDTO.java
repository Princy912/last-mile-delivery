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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String agentName;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private BigDecimal latitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private BigDecimal longitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String vehicleType;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String currentStatus;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime lastUpdated;
}
