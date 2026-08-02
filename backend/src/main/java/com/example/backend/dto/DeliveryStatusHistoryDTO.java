package com.example.backend.dto;

import com.example.backend.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryStatusHistoryDTO {

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    @NotNull
    private OrderStatus status;

    private String comments;

    private LocalDateTime updatedTime;
}
