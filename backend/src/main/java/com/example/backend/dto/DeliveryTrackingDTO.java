package com.example.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryTrackingDTO {

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    @NotNull
    private Long deliveryAgentId;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    private LocalDateTime updatedTime;

    private String status;
}
