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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "10")
    private Long deliveryOrderId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "TRK-987654321")
    private String trackingNumber;

    @NotNull
    @Schema(example = "SIGNATURE")
    private PodType podType;

    @NotBlank
    @Schema(example = "data:image/png;base64,iVBORw0KGgo...")
    private String podData;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime capturedAt;
}