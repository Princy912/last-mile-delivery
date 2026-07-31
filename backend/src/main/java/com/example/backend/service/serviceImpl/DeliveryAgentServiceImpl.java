package com.example.backend.service.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.User;
import com.example.backend.mapper.DeliveryAgentMapper;
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
    public DeliveryAgentDTO create(DeliveryAgent deliveryAgent) {

        User existingUser = userRepository.findById(deliveryAgent.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        deliveryAgent.setUser(existingUser);

        DeliveryAgent savedAgent = deliveryAgentRepository.save(deliveryAgent);
        return DeliveryAgentMapper.toDTO(savedAgent);
    }

    @Override
    public List<DeliveryAgentDTO> getAll() {
        return deliveryAgentRepository.findAll()
                .stream()
                .map(DeliveryAgentMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryAgentDTO getById(Long id) {
        DeliveryAgent agent = deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));
        return DeliveryAgentMapper.toDTO(agent);
    }

    @Override
    public DeliveryAgentDTO update(Long id, DeliveryAgent deliveryAgent) {

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

        DeliveryAgent updatedAgent = deliveryAgentRepository.save(existingDeliveryAgent);
        return DeliveryAgentMapper.toDTO(updatedAgent);
    }

    @Override
    public void delete(Long id) {

        DeliveryAgent deliveryAgent = deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));

        deliveryAgentRepository.delete(deliveryAgent);
    }
}