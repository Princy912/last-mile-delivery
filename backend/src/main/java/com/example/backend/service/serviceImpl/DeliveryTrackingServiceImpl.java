package com.example.backend.service.serviceImpl;

import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.DeliveryTracking;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.DeliveryTrackingMapper;
import com.example.backend.repository.DeliveryAgentRepository;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.DeliveryTrackingRepository;
import com.example.backend.service.DeliveryTrackingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryTrackingServiceImpl implements DeliveryTrackingService {

    private final DeliveryTrackingRepository deliveryTrackingRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    public DeliveryTrackingServiceImpl(
            DeliveryTrackingRepository deliveryTrackingRepository,
            DeliveryOrderRepository deliveryOrderRepository,
            DeliveryAgentRepository deliveryAgentRepository
    ) {
        this.deliveryTrackingRepository = deliveryTrackingRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    @Override
    public DeliveryTrackingDTO create(DeliveryTrackingDTO dto) {
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));
        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        DeliveryTracking entity = DeliveryTrackingMapper.toEntity(dto);
        entity.setDeliveryOrder(order);
        entity.setDeliveryAgent(agent);
        if (entity.getUpdatedTime() == null) {
            entity.setUpdatedTime(LocalDateTime.now());
        }

        DeliveryTracking saved = deliveryTrackingRepository.save(entity);
        return DeliveryTrackingMapper.toDTO(saved);
    }

    @Override
    public List<DeliveryTrackingDTO> getAll() {
        return deliveryTrackingRepository.findAll().stream()
                .map(DeliveryTrackingMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryTrackingDTO getById(Long id) {
        DeliveryTracking entity = deliveryTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Tracking not found"));
        return DeliveryTrackingMapper.toDTO(entity);
    }

    @Override
    public List<DeliveryTrackingDTO> getByDeliveryOrderId(Long orderId) {
        return deliveryTrackingRepository.findByDeliveryOrderId(orderId).stream()
                .map(DeliveryTrackingMapper::toDTO)
                .toList();
    }

    @Override
    public List<DeliveryTrackingDTO> getByDeliveryAgentId(Long agentId) {
        return deliveryTrackingRepository.findByDeliveryAgentId(agentId).stream()
                .map(DeliveryTrackingMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryTrackingDTO update(Long id, DeliveryTrackingDTO dto) {
        DeliveryTracking existing = deliveryTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Tracking not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));
        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        existing.setDeliveryOrder(order);
        existing.setDeliveryAgent(agent);
        existing.setLatitude(dto.getLatitude());
        existing.setLongitude(dto.getLongitude());
        existing.setUpdatedTime(dto.getUpdatedTime() != null ? dto.getUpdatedTime() : LocalDateTime.now());
        existing.setStatus(dto.getStatus());

        DeliveryTracking updated = deliveryTrackingRepository.save(existing);
        return DeliveryTrackingMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        DeliveryTracking existing = deliveryTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Tracking not found"));
        deliveryTrackingRepository.delete(existing);
    }
}
