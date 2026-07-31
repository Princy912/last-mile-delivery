package com.example.backend.dto;

import java.math.BigDecimal;

import com.example.backend.enums.AgentStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgentDTO {

    private Long id;

    private Long userId;

    private String userName;

    private String vehicleType;

    private BigDecimal currentLat;

    private BigDecimal currentLng;

    private AgentStatus status;

    private BigDecimal rating;
}