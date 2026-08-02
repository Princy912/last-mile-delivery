package com.example.backend.controller;

import com.example.backend.dto.DeliveryStatusHistoryDTO;
import com.example.backend.service.DeliveryStatusHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-status-histories")
public class DeliveryStatusHistoryController {

    private final DeliveryStatusHistoryService deliveryStatusHistoryService;

    public DeliveryStatusHistoryController(DeliveryStatusHistoryService deliveryStatusHistoryService) {
        this.deliveryStatusHistoryService = deliveryStatusHistoryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryStatusHistoryDTO> create(@Valid @RequestBody DeliveryStatusHistoryDTO dto) {
        return new ResponseEntity<>(deliveryStatusHistoryService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryStatusHistoryDTO>> getAll() {
        return ResponseEntity.ok(deliveryStatusHistoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryStatusHistoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryStatusHistoryService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DeliveryStatusHistoryDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(deliveryStatusHistoryService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryStatusHistoryDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryStatusHistoryDTO dto) {
        return ResponseEntity.ok(deliveryStatusHistoryService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryStatusHistoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
