package com.example.backend.dto;

import com.example.backend.enums.RouteStopStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteStopDTO {

    private Long id;

    @NotNull
    private Long routeId;

    @NotNull
    private Long deliveryOrderId;

    @NotNull
    private Integer sequence;

    @NotNull
    private RouteStopStatus status;

    private LocalDateTime estimatedArrivalTime;

    private LocalDateTime actualArrivalTime;
}
