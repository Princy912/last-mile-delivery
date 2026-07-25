package com.example.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

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
    public DeliveryAgent createDeliveryAgent(@RequestBody DeliveryAgent deliveryAgent) {
        return deliveryAgentService.createDeliveryAgent(deliveryAgent);
    }

    @GetMapping
    public List<DeliveryAgent> getAllDeliveryAgents() {
        return deliveryAgentService.getAllDeliveryAgents();
    }

    @GetMapping("/{id}")
    public DeliveryAgent getDeliveryAgentById(@PathVariable Long id) {
        return deliveryAgentService.getDeliveryAgentById(id);
    }

    @PutMapping("/{id}")
    public DeliveryAgent updateDeliveryAgent(@PathVariable Long id,
                                             @RequestBody DeliveryAgent deliveryAgent) {
        return deliveryAgentService.updateDeliveryAgent(id, deliveryAgent);
    }

    @DeleteMapping("/{id}")
    public void deleteDeliveryAgent(@PathVariable Long id) {
        deliveryAgentService.deleteDeliveryAgent(id);
    }
}