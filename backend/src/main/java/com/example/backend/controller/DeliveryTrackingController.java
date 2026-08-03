package com.example.backend.controller;

import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.service.DeliveryTrackingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/delivery-trackings")
public class DeliveryTrackingController {

    private final DeliveryTrackingService deliveryTrackingService;

    public DeliveryTrackingController(DeliveryTrackingService deliveryTrackingService) {
        this.deliveryTrackingService = deliveryTrackingService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<DeliveryTrackingDTO> create(@Valid @RequestBody DeliveryTrackingDTO dto) {
        return new ResponseEntity<>(deliveryTrackingService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<List<DeliveryTrackingDTO>> getAll() {
        return ResponseEntity.ok(deliveryTrackingService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<DeliveryTrackingDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure CUSTOMER can only read tracking of their own orders
        return ResponseEntity.ok(deliveryTrackingService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<List<DeliveryTrackingDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership validation to ensure CUSTOMER can only read tracking of their own orders
        return ResponseEntity.ok(deliveryTrackingService.getByDeliveryOrderId(orderId));
    }

    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<List<DeliveryTrackingDTO>> getByDeliveryAgentId(@PathVariable Long agentId) {
        // TODO: Implement ownership validation to ensure CUSTOMER can only read tracking of their own orders
        return ResponseEntity.ok(deliveryTrackingService.getByDeliveryAgentId(agentId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<DeliveryTrackingDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryTrackingDTO dto) {
        // TODO: Implement ownership check to ensure DELIVERY_AGENT can only update tracking for their assigned deliveries
        return ResponseEntity.ok(deliveryTrackingService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryTrackingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
