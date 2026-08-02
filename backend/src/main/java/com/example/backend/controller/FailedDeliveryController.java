package com.example.backend.controller;

import com.example.backend.dto.FailedDeliveryDTO;
import com.example.backend.service.FailedDeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/failed-deliveries")
public class FailedDeliveryController {

    private final FailedDeliveryService failedDeliveryService;

    public FailedDeliveryController(FailedDeliveryService failedDeliveryService) {
        this.failedDeliveryService = failedDeliveryService;
    }

    @PostMapping
    public ResponseEntity<FailedDeliveryDTO> create(@Valid @RequestBody FailedDeliveryDTO dto) {
        return new ResponseEntity<>(failedDeliveryService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FailedDeliveryDTO>> getAll() {
        return ResponseEntity.ok(failedDeliveryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FailedDeliveryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(failedDeliveryService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<FailedDeliveryDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(failedDeliveryService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FailedDeliveryDTO> update(@PathVariable Long id, @Valid @RequestBody FailedDeliveryDTO dto) {
        return ResponseEntity.ok(failedDeliveryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        failedDeliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
