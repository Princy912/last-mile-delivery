package com.example.backend.dto;

import java.math.BigDecimal;

import com.example.backend.enums.AgentStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgentDTO {

    private Long id;

    @NotNull
    private Long userId;

    private String userName;

    @NotBlank
    private String vehicleType;

    @NotNull
    private BigDecimal currentLat;

    @NotNull
    private BigDecimal currentLng;

    @NotNull
    private AgentStatus status;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private BigDecimal rating;
}