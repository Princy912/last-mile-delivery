package com.example.backend.service;

import java.util.List;

import com.example.backend.entity.DeliveryAgent;

public interface DeliveryAgentService {

    DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent);

    List<DeliveryAgent> getAllDeliveryAgents();

    DeliveryAgent getDeliveryAgentById(Long id);

    DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent);

    void deleteDeliveryAgent(Long id);
}