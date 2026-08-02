package com.example.backend.dto;

import com.example.backend.enums.PayoutStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentPayoutDTO {

    private Long id;

    @NotNull
    private Long deliveryAgentId;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private PayoutStatus status;

    private String transactionReference;

    private LocalDateTime paidAt;
}
