package com.example.backend.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.User;
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
    public DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder) {

        User customer = userRepository.findById(deliveryOrder.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        DeliveryAgent agent = deliveryAgentRepository.findById(deliveryOrder.getDeliveryAgent().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));

        deliveryOrder.setCustomer(customer);
        deliveryOrder.setDeliveryAgent(agent);
        deliveryOrder.setCreatedAt(LocalDateTime.now());

        return deliveryOrderRepository.save(deliveryOrder);
    }

    @Override
    public List<DeliveryOrder> getAllDeliveryOrders() {
        return deliveryOrderRepository.findAll();
    }

    @Override
    public DeliveryOrder getDeliveryOrderById(Long id) {
        return deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));
    }

    @Override
    public DeliveryOrder updateDeliveryOrder(Long id, DeliveryOrder deliveryOrder) {

        DeliveryOrder existingOrder = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        User customer = userRepository.findById(deliveryOrder.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        DeliveryAgent agent = deliveryAgentRepository.findById(deliveryOrder.getDeliveryAgent().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Agent not found"));

        existingOrder.setCustomer(customer);
        existingOrder.setDeliveryAgent(agent);
        existingOrder.setTrackingNumber(deliveryOrder.getTrackingNumber());
        existingOrder.setPickupAddress(deliveryOrder.getPickupAddress());
        existingOrder.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
        existingOrder.setStatus(deliveryOrder.getStatus());
        existingOrder.setPriority(deliveryOrder.getPriority());
        existingOrder.setEstimatedDeliveryTime(deliveryOrder.getEstimatedDeliveryTime());
        existingOrder.setActualDeliveryTime(deliveryOrder.getActualDeliveryTime());

        return deliveryOrderRepository.save(existingOrder);
    }

    @Override
    public void deleteDeliveryOrder(Long id) {

        DeliveryOrder order = deliveryOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        deliveryOrderRepository.delete(order);
    }
}