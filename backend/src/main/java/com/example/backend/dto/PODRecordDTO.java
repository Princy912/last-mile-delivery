package com.example.backend.dto;

import com.example.backend.enums.PodType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PODRecordDTO {

    private Long id;

    private Long deliveryOrderId;

    private String trackingNumber;

    private PodType podType;

    private String podData;

    private LocalDateTime capturedAt;
}