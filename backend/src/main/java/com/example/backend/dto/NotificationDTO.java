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
public class NotificationDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "2")
    private Long userId;

    @Schema(example = "10")
    private Long deliveryOrderId;

    @NotBlank
    @Schema(example = "Order Dispatched")
    private String title;

    @NotBlank
    @Schema(example = "Your order TRK-987654321 has been dispatched.")
    private String message;

    @Schema(example = "false")
    private Boolean isRead;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime createdAt;
}
