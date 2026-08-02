package com.example.backend.dto;

import com.example.backend.enums.RouteStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {

    private Long id;

    @NotNull
    private Long deliveryAgentId;

    @NotBlank
    private String routeName;

    @NotNull
    private BigDecimal startLat;

    @NotNull
    private BigDecimal startLng;

    @NotNull
    private BigDecimal endLat;

    @NotNull
    private BigDecimal endLng;

    @NotNull
    private RouteStatus status;

    private LocalDateTime createdAt;
}
