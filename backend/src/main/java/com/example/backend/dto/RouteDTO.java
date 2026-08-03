package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.enums.RouteStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "5")
    private Long deliveryAgentId;

    @NotBlank
    @Schema(example = "Route-North-A")
    private String routeName;

    @NotNull
    @Schema(example = "37.7749000")
    private BigDecimal startLat;

    @NotNull
    @Schema(example = "-122.4194000")
    private BigDecimal startLng;

    @NotNull
    @Schema(example = "37.8049000")
    private BigDecimal endLat;

    @NotNull
    @Schema(example = "-122.4094000")
    private BigDecimal endLng;

    @NotNull
    @Schema(example = "PENDING")
    private RouteStatus status;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime createdAt;
}
