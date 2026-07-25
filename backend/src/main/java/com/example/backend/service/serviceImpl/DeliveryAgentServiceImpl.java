package com.example.backend.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.User;
import com.example.backend.repository.DeliveryAgentRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DeliveryAgentService;

@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    private final DeliveryAgentRepository deliveryAgentRepository;
    private final UserRepository userRepository;

    public DeliveryAgentServiceImpl(DeliveryAgentRepository deliveryAgentRepository,
                                    UserRepository userRepository) {
        this.deliveryAgentRepository = deliveryAgentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent) {

        User existingUser = userRepository.findById(deliveryAgent.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        deliveryAgent.setUser(existingUser);

        return deliveryAgentRepository.save(deliveryAgent);
    }

    @Override
    public List<DeliveryAgent> getAllDeliveryAgents() {
        return deliveryAgentRepository.findAll();
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(Long id) {
        return deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));
    }

    @Override
    public DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent) {

        DeliveryAgent existingDeliveryAgent = deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));

        User existingUser = userRepository.findById(deliveryAgent.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingDeliveryAgent.setUser(existingUser);
        existingDeliveryAgent.setVehicleType(deliveryAgent.getVehicleType());
        existingDeliveryAgent.setCurrentLat(deliveryAgent.getCurrentLat());
        existingDeliveryAgent.setCurrentLng(deliveryAgent.getCurrentLng());
        existingDeliveryAgent.setStatus(deliveryAgent.getStatus());
        existingDeliveryAgent.setRating(deliveryAgent.getRating());

        return deliveryAgentRepository.save(existingDeliveryAgent);
    }

    @Override
    public void deleteDeliveryAgent(Long id) {

        DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));

        deliveryAgentRepository.delete(deliveryAgent);
    }
}