package com.example.backend.dto;

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

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    private String trackingNumber;

    @NotNull
    private PodType podType;

    @NotBlank
    private String podData;

    private LocalDateTime capturedAt;
}