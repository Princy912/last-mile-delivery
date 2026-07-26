package com.example.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOrder createDeliveryOrder(@RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderService.createDeliveryOrder(deliveryOrder);
    }

    @GetMapping
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderService.getAllDeliveryOrders();
    }

    @GetMapping("/{id}")
    public DeliveryOrder getDeliveryOrderById(@PathVariable Long id) {
        return deliveryOrderService.getDeliveryOrderById(id);
    }

    @PutMapping("/{id}")
    public DeliveryOrder updateDeliveryOrder(@PathVariable Long id,
                                             @RequestBody DeliveryOrder deliveryOrder) {
        return deliveryOrderService.updateDeliveryOrder(id, deliveryOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryOrder(@PathVariable Long id) {
        deliveryOrderService.deleteDeliveryOrder(id);
    }
}