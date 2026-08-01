package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.User;
import com.example.backend.service.DeliveryAgentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/delivery-agents")
public class DeliveryAgentController {

    private final DeliveryAgentService deliveryAgentService;

    public DeliveryAgentController(DeliveryAgentService deliveryAgentService) {
        this.deliveryAgentService = deliveryAgentService;
    }

    @PostMapping
    public ResponseEntity<DeliveryAgentDTO> createDeliveryAgent(@Valid @RequestBody DeliveryAgentDTO dto) {
        DeliveryAgent deliveryAgent = DeliveryAgent.builder()
                .user(User.builder().id(dto.getUserId()).build())
                .vehicleType(dto.getVehicleType())
                .currentLat(dto.getCurrentLat())
                .currentLng(dto.getCurrentLng())
                .status(dto.getStatus())
                .rating(dto.getRating())
                .build();
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
                                                                @Valid @RequestBody DeliveryAgentDTO dto) {
        DeliveryAgent deliveryAgent = DeliveryAgent.builder()
                .user(User.builder().id(dto.getUserId()).build())
                .vehicleType(dto.getVehicleType())
                .currentLat(dto.getCurrentLat())
                .currentLng(dto.getCurrentLng())
                .status(dto.getStatus())
                .rating(dto.getRating())
                .build();
        return ResponseEntity.ok(deliveryAgentService.update(id, deliveryAgent));
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryAgent(@PathVariable Long id) {
        deliveryAgentService.delete(id);
    }
}