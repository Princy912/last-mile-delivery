package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.User;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.service.DeliveryOrderService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/delivery-orders")
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<DeliveryOrderDTO> createDeliveryOrder(@Valid @RequestBody DeliveryOrderDTO dto) {
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .customer(User.builder().id(dto.getCustomerId()).build())
                .deliveryAgent(DeliveryAgent.builder().id(dto.getDeliveryAgentId()).build())
                .trackingNumber(dto.getTrackingNumber())
                .pickupAddress(dto.getPickupAddress())
                .deliveryAddress(dto.getDeliveryAddress())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .estimatedDeliveryTime(dto.getEstimatedDeliveryTime())
                .actualDeliveryTime(dto.getActualDeliveryTime())
                .build();
        return new ResponseEntity<>(deliveryOrderService.create(deliveryOrder), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<List<DeliveryOrderDTO>> getAllDeliveryOrders() {
        return ResponseEntity.ok(deliveryOrderService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER', 'DELIVERY_AGENT')")
    public ResponseEntity<DeliveryOrderDTO> getDeliveryOrderById(@PathVariable Long id) {
        // TODO: Implement ownership/assignment validation (CUSTOMER can only read own orders, DELIVERY_AGENT can only read assigned orders)
        return ResponseEntity.ok(deliveryOrderService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<DeliveryOrderDTO> updateDeliveryOrder(@PathVariable Long id,
                                                                @Valid @RequestBody DeliveryOrderDTO dto) {
        DeliveryOrder deliveryOrder = DeliveryOrder.builder()
                .customer(User.builder().id(dto.getCustomerId()).build())
                .deliveryAgent(DeliveryAgent.builder().id(dto.getDeliveryAgentId()).build())
                .trackingNumber(dto.getTrackingNumber())
                .pickupAddress(dto.getPickupAddress())
                .deliveryAddress(dto.getDeliveryAddress())
                .status(dto.getStatus())
                .priority(dto.getPriority())
                .estimatedDeliveryTime(dto.getEstimatedDeliveryTime())
                .actualDeliveryTime(dto.getActualDeliveryTime())
                .build();
        return ResponseEntity.ok(deliveryOrderService.update(id, deliveryOrder));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDeliveryOrder(@PathVariable Long id) {
        deliveryOrderService.delete(id);
    }
}