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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long userId;

    @NotBlank
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String action;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String details;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String ipAddress;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime timestamp;
}
