package com.example.backend.controller;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DISPATCHER', 'ADMIN')")
    public ResponseEntity<NotificationDTO> create(@Valid @RequestBody NotificationDTO dto) {
        return new ResponseEntity<>(notificationService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER', 'DELIVERY_AGENT')")
    public ResponseEntity<NotificationDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure CUSTOMER and DELIVERY_AGENT can only read their own notifications
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER', 'DELIVERY_AGENT')")
    public ResponseEntity<List<NotificationDTO>> getByUserId(@PathVariable Long userId) {
        // TODO: Implement ownership validation to ensure CUSTOMER and DELIVERY_AGENT can only read their own notifications
        return ResponseEntity.ok(notificationService.getByUserId(userId));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER', 'DELIVERY_AGENT')")
    public ResponseEntity<List<NotificationDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership validation to ensure CUSTOMER and DELIVERY_AGENT can only read their own notifications
        return ResponseEntity.ok(notificationService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DISPATCHER', 'ADMIN')")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @Valid @RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
