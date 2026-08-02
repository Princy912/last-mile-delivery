package com.example.backend.dto;

import com.example.backend.enums.ReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnRecordDTO {

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    @NotBlank
    private String reason;

    @NotNull
    private ReturnStatus status;

    private Boolean refundProcessed;

    private LocalDateTime requestedAt;
}
