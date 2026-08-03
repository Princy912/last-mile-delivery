package com.example.backend.controller;

import com.example.backend.dto.FailedDeliveryDTO;
import com.example.backend.service.FailedDeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/failed-deliveries")
public class FailedDeliveryController {

    private final FailedDeliveryService failedDeliveryService;

    public FailedDeliveryController(FailedDeliveryService failedDeliveryService) {
        this.failedDeliveryService = failedDeliveryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<FailedDeliveryDTO> create(@Valid @RequestBody FailedDeliveryDTO dto) {
        return new ResponseEntity<>(failedDeliveryService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<FailedDeliveryDTO>> getAll() {
        return ResponseEntity.ok(failedDeliveryService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'CUSTOMER')")
    public ResponseEntity<FailedDeliveryDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership check to ensure CUSTOMER can only read failed delivery records for their own orders
        return ResponseEntity.ok(failedDeliveryService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'CUSTOMER')")
    public ResponseEntity<List<FailedDeliveryDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership check to ensure CUSTOMER can only read failed delivery records for their own orders
        return ResponseEntity.ok(failedDeliveryService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DISPATCHER', 'ADMIN')")
    public ResponseEntity<FailedDeliveryDTO> update(@PathVariable Long id, @Valid @RequestBody FailedDeliveryDTO dto) {
        return ResponseEntity.ok(failedDeliveryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        failedDeliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
