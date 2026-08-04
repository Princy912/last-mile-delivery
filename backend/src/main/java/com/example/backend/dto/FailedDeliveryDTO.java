package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailedDeliveryDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long deliveryOrderId;

    @NotBlank
    @Schema(example = "string")
    private String reason;

    @Schema(example = "string")
    private String notes;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime attemptedTime;
}
