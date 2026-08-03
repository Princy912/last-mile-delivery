package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.enums.ReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnRecordDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "10")
    private Long deliveryOrderId;

    @NotBlank
    @Schema(example = "Item damaged during transit")
    private String reason;

    @NotNull
    @Schema(example = "PENDING")
    private ReturnStatus status;

    @Schema(example = "false")
    private Boolean refundProcessed;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime requestedAt;
}
