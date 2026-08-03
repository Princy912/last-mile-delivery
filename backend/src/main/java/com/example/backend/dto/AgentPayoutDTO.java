package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "1")
    private Long deliveryAgentId;

    @NotNull
    @Schema(example = "150.00")
    private BigDecimal amount;

    @NotNull
    @Schema(example = "PENDING")
    private PayoutStatus status;

    @Schema(example = "TXN-987654321")
    private String transactionReference;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime paidAt;
}
