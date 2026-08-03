package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrackingResponse {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "10")
    private Long orderId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "TRANSIT")
    private String currentStatus;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "37.7749000")
    private BigDecimal latitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "-122.4194000")
    private BigDecimal longitude;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime lastUpdated;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<TrackingHistoryItem> trackingHistory;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrackingHistoryItem {

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "TRANSIT")
        private String status;

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "37.7749000")
        private BigDecimal latitude;

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "-122.4194000")
        private BigDecimal longitude;

        @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
        private LocalDateTime updatedTime;
    }
}
