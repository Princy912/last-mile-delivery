package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FailedDeliveryDTO {

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    @NotBlank
    private String reason;

    private String notes;

    private LocalDateTime attemptedTime;
}
