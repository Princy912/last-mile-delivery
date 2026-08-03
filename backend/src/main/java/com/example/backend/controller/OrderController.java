package com.example.backend.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.dto.OrderTrackingResponse;
import com.example.backend.service.DeliveryTrackingService;
import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.enums.OrderStatus;
import com.example.backend.service.DeliveryOrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final DeliveryOrderService deliveryOrderService;
    private final DeliveryTrackingService deliveryTrackingService;

    public OrderController(DeliveryOrderService deliveryOrderService, DeliveryTrackingService deliveryTrackingService) {
        this.deliveryOrderService = deliveryOrderService;
        this.deliveryTrackingService = deliveryTrackingService;
    }

    @GetMapping("/unassigned")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<DeliveryOrderDTO>> getUnassignedOrders() {
        return ResponseEntity.ok(deliveryOrderService.getUnassignedOrders());
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<DeliveryOrderDTO> assignOrder(
            @PathVariable Long id,
            @Valid @RequestBody AssignOrderRequest request
    ) {
        return ResponseEntity.ok(deliveryOrderService.assignOrder(id, request.getDeliveryAgentId()));
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER', 'DELIVERY_AGENT')")
    public ResponseEntity<DeliveryOrderDTO> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request
    ) {
        return ResponseEntity.ok(deliveryOrderService.updateOrderStatus(id, request.getStatus()));
    }

    @GetMapping("/{id}/track")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER', 'CUSTOMER', 'DELIVERY_AGENT')")
    public ResponseEntity<OrderTrackingResponse> trackOrder(@PathVariable Long id) {
        DeliveryOrderDTO order = deliveryOrderService.getById(id);
        List<DeliveryTrackingDTO> trackingList = deliveryTrackingService.getByDeliveryOrderId(id);

        List<OrderTrackingResponse.TrackingHistoryItem> history = trackingList.stream()
                .sorted(Comparator.comparing(DeliveryTrackingDTO::getUpdatedTime).reversed())
                .map(t -> OrderTrackingResponse.TrackingHistoryItem.builder()
                        .status(t.getStatus() != null ? t.getStatus().name() : order.getStatus().name())
                        .latitude(t.getLatitude())
                        .longitude(t.getLongitude())
                        .updatedTime(t.getUpdatedTime())
                        .build())
                .collect(Collectors.toList());

        OrderTrackingResponse.OrderTrackingResponseBuilder builder = OrderTrackingResponse.builder()
                .orderId(id)
                .currentStatus(order.getStatus().name())
                .trackingHistory(history);

        if (!trackingList.isEmpty()) {
            DeliveryTrackingDTO latest = trackingList.stream()
                    .max(Comparator.comparing(DeliveryTrackingDTO::getUpdatedTime))
                    .orElse(trackingList.get(0));
            builder.latitude(latest.getLatitude())
                   .longitude(latest.getLongitude())
                   .lastUpdated(latest.getUpdatedTime());
        }

        return ResponseEntity.ok(builder.build());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AssignOrderRequest {
        @NotNull(message = "deliveryAgentId must not be null")
        @Schema(example = "5")
        private Long deliveryAgentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusRequest {
        @NotNull(message = "status must not be null")
        @Schema(example = "DELIVERED")
        private OrderStatus status;
    }
}
