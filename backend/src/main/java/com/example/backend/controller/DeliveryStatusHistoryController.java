package com.example.backend.controller;

import com.example.backend.dto.DeliveryStatusHistoryDTO;
import com.example.backend.service.DeliveryStatusHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/delivery-status-histories")
public class DeliveryStatusHistoryController {

    private final DeliveryStatusHistoryService deliveryStatusHistoryService;

    public DeliveryStatusHistoryController(DeliveryStatusHistoryService deliveryStatusHistoryService) {
        this.deliveryStatusHistoryService = deliveryStatusHistoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DISPATCHER', 'DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<DeliveryStatusHistoryDTO> create(@Valid @RequestBody DeliveryStatusHistoryDTO dto) {
        return new ResponseEntity<>(deliveryStatusHistoryService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<List<DeliveryStatusHistoryDTO>> getAll() {
        return ResponseEntity.ok(deliveryStatusHistoryService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<DeliveryStatusHistoryDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure CUSTOMER can only read status history of their own orders
        return ResponseEntity.ok(deliveryStatusHistoryService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<List<DeliveryStatusHistoryDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership validation to ensure CUSTOMER can only read status history of their own orders
        return ResponseEntity.ok(deliveryStatusHistoryService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<DeliveryStatusHistoryDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryStatusHistoryDTO dto) {
        return ResponseEntity.ok(deliveryStatusHistoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryStatusHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
