package com.example.backend.controller;

import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.service.DeliveryTrackingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-trackings")
public class DeliveryTrackingController {

    private final DeliveryTrackingService deliveryTrackingService;

    public DeliveryTrackingController(DeliveryTrackingService deliveryTrackingService) {
        this.deliveryTrackingService = deliveryTrackingService;
    }

    @PostMapping
    public ResponseEntity<DeliveryTrackingDTO> create(@Valid @RequestBody DeliveryTrackingDTO dto) {
        return new ResponseEntity<>(deliveryTrackingService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryTrackingDTO>> getAll() {
        return ResponseEntity.ok(deliveryTrackingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryTrackingDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryTrackingService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<DeliveryTrackingDTO>> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(deliveryTrackingService.getByDeliveryOrderId(orderId));
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<DeliveryTrackingDTO>> getByDeliveryAgentId(@PathVariable Long agentId) {
        return ResponseEntity.ok(deliveryTrackingService.getByDeliveryAgentId(agentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryTrackingDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryTrackingDTO dto) {
        return ResponseEntity.ok(deliveryTrackingService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryTrackingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
