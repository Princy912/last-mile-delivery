package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.service.DeliveryOrderService;

@RestController
@RequestMapping("/api/delivery-orders")
public class DeliveryOrderController {

    private final DeliveryOrderService deliveryOrderService;

    public DeliveryOrderController(DeliveryOrderService deliveryOrderService) {
        this.deliveryOrderService = deliveryOrderService;
    }

    @PostMapping
    public ResponseEntity<DeliveryOrderDTO> createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        return new ResponseEntity<>(deliveryOrderService.create(deliveryOrder), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryOrderDTO>> getAllDeliveryOrders() {
        return ResponseEntity.ok(deliveryOrderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOrderDTO> getDeliveryOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryOrderService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryOrderDTO> updateDeliveryOrder(@PathVariable Long id,
                                                                @RequestBody DeliveryOrder deliveryOrder) {
        return ResponseEntity.ok(deliveryOrderService.update(id, deliveryOrder));
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryOrder(@PathVariable Long id) {
        deliveryOrderService.delete(id);
    }
}