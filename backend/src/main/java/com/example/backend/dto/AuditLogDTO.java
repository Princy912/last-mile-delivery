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
public class AuditLogDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2")
    private Long userId;

    @NotBlank
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "UPDATE_ORDER_STATUS")
    private String action;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "Updated order 10 status to TRANSIT")
    private String details;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "192.168.1.5")
    private String ipAddress;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime timestamp;
}
