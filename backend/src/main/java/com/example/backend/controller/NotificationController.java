package com.example.backend.controller;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> create(@Valid @RequestBody NotificationDTO dto) {
        return new ResponseEntity<>(notificationService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getByUserId(userId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<NotificationDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(notificationService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> update(@PathVariable Long id, @Valid @RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
