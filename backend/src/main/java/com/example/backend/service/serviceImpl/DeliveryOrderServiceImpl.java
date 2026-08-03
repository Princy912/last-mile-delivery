package com.example.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.User;
import com.example.backend.mapper.DeliveryOrderMapper;
import com.example.backend.repository.DeliveryAgentRepository;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.DeliveryOrderService;

@Service
public class DeliveryOrderServiceImpl implements DeliveryOrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final UserRepository userRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    public DeliveryOrderServiceImpl(
            DeliveryOrderRepository deliveryOrderRepository,
            UserRepository userRepository,
            DeliveryAgentRepository deliveryAgentRepository) {

        this.deliveryOrderRepository = deliveryOrderRepository;
        this.userRepository = userRepository;
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    @Override
    public DeliveryOrderDTO create(DeliveryOrder deliveryOrder) {
        if (deliveryOrderRepository.existsByTrackingNumber(deliveryOrder.getTrackingNumber())) {
            throw new DuplicateResourceException("Tracking number already exists.");
        }

        User customer = userRepository.findById(deliveryOrder.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        DeliveryAgent agent = deliveryAgentRepository.findById(deliveryOrder.getDeliveryAgent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        deliveryOrder.setCustomer(customer);
        deliveryOrder.setDeliveryAgent(agent);
        deliveryOrder.setCreatedAt(LocalDateTime.now());

        DeliveryOrder savedOrder = deliveryOrderRepository.save(deliveryOrder);
        return DeliveryOrderMapper.toDTO(savedOrder);
    }

    @Override
    public List<DeliveryOrderDTO> getAll() {
        return deliveryOrderRepository.findAll()
                .stream()
                .map(DeliveryOrderMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryOrderDTO getById(Long id) {
        DeliveryOrder order = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));
        return DeliveryOrderMapper.toDTO(order);
    }

    @Override
    public DeliveryOrderDTO update(Long id, DeliveryOrder deliveryOrder) {

        DeliveryOrder existingOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        deliveryOrderRepository.findByTrackingNumber(deliveryOrder.getTrackingNumber())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new DuplicateResourceException("Tracking number already exists.");
                    }
                });

        User customer = userRepository.findById(deliveryOrder.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        DeliveryAgent agent = deliveryAgentRepository.findById(deliveryOrder.getDeliveryAgent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        existingOrder.setCustomer(customer);
        existingOrder.setDeliveryAgent(agent);
        existingOrder.setTrackingNumber(deliveryOrder.getTrackingNumber());
        existingOrder.setPickupAddress(deliveryOrder.getPickupAddress());
        existingOrder.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
        existingOrder.setStatus(deliveryOrder.getStatus());
        existingOrder.setPriority(deliveryOrder.getPriority());
        existingOrder.setEstimatedDeliveryTime(deliveryOrder.getEstimatedDeliveryTime());
        existingOrder.setActualDeliveryTime(deliveryOrder.getActualDeliveryTime());

        DeliveryOrder updatedOrder = deliveryOrderRepository.save(existingOrder);
        return DeliveryOrderMapper.toDTO(updatedOrder);
    }

    @Override
    public void delete(Long id) {

        DeliveryOrder order = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        deliveryOrderRepository.delete(order);
    }
}