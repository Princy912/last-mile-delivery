package com.example.backend.service.serviceImpl;

import com.example.backend.dto.FailedDeliveryDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.FailedDelivery;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.FailedDeliveryMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.FailedDeliveryRepository;
import com.example.backend.service.FailedDeliveryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FailedDeliveryServiceImpl implements FailedDeliveryService {

    private final FailedDeliveryRepository failedDeliveryRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public FailedDeliveryServiceImpl(
            FailedDeliveryRepository failedDeliveryRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.failedDeliveryRepository = failedDeliveryRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public FailedDeliveryDTO create(FailedDeliveryDTO dto) {
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        FailedDelivery entity = FailedDeliveryMapper.toEntity(dto);
        entity.setDeliveryOrder(order);
        if (entity.getAttemptedTime() == null) {
            entity.setAttemptedTime(LocalDateTime.now());
        }

        FailedDelivery saved = failedDeliveryRepository.save(entity);
        return FailedDeliveryMapper.toDTO(saved);
    }

    @Override
    public List<FailedDeliveryDTO> getAll() {
        return failedDeliveryRepository.findAll().stream()
                .map(FailedDeliveryMapper::toDTO)
                .toList();
    }

    @Override
    public FailedDeliveryDTO getById(Long id) {
        FailedDelivery entity = failedDeliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed Delivery record not found"));
        return FailedDeliveryMapper.toDTO(entity);
    }

    @Override
    public List<FailedDeliveryDTO> getByDeliveryOrderId(Long orderId) {
        return failedDeliveryRepository.findByDeliveryOrderId(orderId).stream()
                .map(FailedDeliveryMapper::toDTO)
                .toList();
    }

    @Override
    public FailedDeliveryDTO update(Long id, FailedDeliveryDTO dto) {
        FailedDelivery existing = failedDeliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed Delivery record not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setReason(dto.getReason());
        existing.setNotes(dto.getNotes());
        existing.setAttemptedTime(dto.getAttemptedTime() != null ? dto.getAttemptedTime() : LocalDateTime.now());

        FailedDelivery updated = failedDeliveryRepository.save(existing);
        return FailedDeliveryMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        FailedDelivery existing = failedDeliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Failed Delivery record not found"));
        failedDeliveryRepository.delete(existing);
    }
}
