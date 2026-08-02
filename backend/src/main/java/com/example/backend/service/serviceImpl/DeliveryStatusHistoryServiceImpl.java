package com.example.backend.service.serviceImpl;

import com.example.backend.dto.DeliveryStatusHistoryDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.DeliveryStatusHistory;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.DeliveryStatusHistoryMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.DeliveryStatusHistoryRepository;
import com.example.backend.service.DeliveryStatusHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryStatusHistoryServiceImpl implements DeliveryStatusHistoryService {

    private final DeliveryStatusHistoryRepository deliveryStatusHistoryRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public DeliveryStatusHistoryServiceImpl(
            DeliveryStatusHistoryRepository deliveryStatusHistoryRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.deliveryStatusHistoryRepository = deliveryStatusHistoryRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public DeliveryStatusHistoryDTO create(DeliveryStatusHistoryDTO dto) {
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        DeliveryStatusHistory entity = DeliveryStatusHistoryMapper.toEntity(dto);
        entity.setDeliveryOrder(order);
        if (entity.getUpdatedTime() == null) {
            entity.setUpdatedTime(LocalDateTime.now());
        }

        DeliveryStatusHistory saved = deliveryStatusHistoryRepository.save(entity);
        return DeliveryStatusHistoryMapper.toDTO(saved);
    }

    @Override
    public List<DeliveryStatusHistoryDTO> getAll() {
        return deliveryStatusHistoryRepository.findAll().stream()
                .map(DeliveryStatusHistoryMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryStatusHistoryDTO getById(Long id) {
        DeliveryStatusHistory entity = deliveryStatusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Status History not found"));
        return DeliveryStatusHistoryMapper.toDTO(entity);
    }

    @Override
    public List<DeliveryStatusHistoryDTO> getByDeliveryOrderId(Long orderId) {
        return deliveryStatusHistoryRepository.findByDeliveryOrderId(orderId).stream()
                .map(DeliveryStatusHistoryMapper::toDTO)
                .toList();
    }

    @Override
    public DeliveryStatusHistoryDTO update(Long id, DeliveryStatusHistoryDTO dto) {
        DeliveryStatusHistory existing = deliveryStatusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Status History not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setStatus(dto.getStatus());
        existing.setComments(dto.getComments());
        existing.setUpdatedTime(dto.getUpdatedTime() != null ? dto.getUpdatedTime() : LocalDateTime.now());

        DeliveryStatusHistory updated = deliveryStatusHistoryRepository.save(existing);
        return DeliveryStatusHistoryMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        DeliveryStatusHistory existing = deliveryStatusHistoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Status History not found"));
        deliveryStatusHistoryRepository.delete(existing);
    }
}
