package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.service.DeliveryAgentService;

@RestController
@RequestMapping("/api/delivery-agents")
public class DeliveryAgentController {

    private final DeliveryAgentService deliveryAgentService;

    public DeliveryAgentController(DeliveryAgentService deliveryAgentService) {
        this.deliveryAgentService = deliveryAgentService;
    }

    @PostMapping
    public ResponseEntity<DeliveryAgentDTO> createDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent) {
        return new ResponseEntity<>(deliveryAgentService.create(deliveryAgent), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAgentDTO>> getAllDeliveryAgents() {
        return ResponseEntity.ok(deliveryAgentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgentDTO> getDeliveryAgentById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryAgentService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAgentDTO> updateDeliveryAgent(@PathVariable Long id,
                                                                @RequestBody DeliveryAgent deliveryAgent) {
        return ResponseEntity.ok(deliveryAgentService.update(id, deliveryAgent));
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryAgent(@PathVariable Long id) {
        deliveryAgentService.delete(id);
    }
}