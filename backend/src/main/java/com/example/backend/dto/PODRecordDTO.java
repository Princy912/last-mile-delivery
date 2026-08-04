package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.enums.PodType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PODRecordDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long deliveryOrderId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String trackingNumber;

    @NotNull
    @Schema(example = "SIGNATURE")
    private PodType podType;

    @NotBlank
    @Schema(example = "string")
    private String podData;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime capturedAt;
}