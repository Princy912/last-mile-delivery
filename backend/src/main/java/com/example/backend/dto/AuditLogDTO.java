package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogDTO {

    private Long id;

    @NotNull
    private Long userId;

    @NotBlank
    private String action;

    private String details;

    private String ipAddress;

    private LocalDateTime timestamp;
}
