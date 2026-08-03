package com.example.backend.controller;

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

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final DeliveryOrderService deliveryOrderService;

    public OrderController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AssignOrderRequest {
        @NotNull(message = "deliveryAgentId must not be null")
        private Long deliveryAgentId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateStatusRequest {
        @NotNull(message = "status must not be null")
        private OrderStatus status;
    }
}
