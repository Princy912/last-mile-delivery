package com.example.backend.service.serviceImpl;

import com.example.backend.dto.ReturnRecordDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.ReturnRecord;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.ReturnRecordMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.ReturnRecordRepository;
import com.example.backend.service.ReturnRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReturnRecordServiceImpl implements ReturnRecordService {

    private final ReturnRecordRepository returnRecordRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public ReturnRecordServiceImpl(
            ReturnRecordRepository returnRecordRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.returnRecordRepository = returnRecordRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public ReturnRecordDTO create(ReturnRecordDTO dto) {
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        ReturnRecord entity = ReturnRecordMapper.toEntity(dto);
        entity.setDeliveryOrder(order);
        if (entity.getRequestedAt() == null) {
            entity.setRequestedAt(LocalDateTime.now());
        }

        ReturnRecord saved = returnRecordRepository.save(entity);
        return ReturnRecordMapper.toDTO(saved);
    }

    @Override
    public List<ReturnRecordDTO> getAll() {
        return returnRecordRepository.findAll().stream()
                .map(ReturnRecordMapper::toDTO)
                .toList();
    }

    @Override
    public ReturnRecordDTO getById(Long id) {
        ReturnRecord entity = returnRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Return record not found"));
        return ReturnRecordMapper.toDTO(entity);
    }

    @Override
    public ReturnRecordDTO getByDeliveryOrderId(Long orderId) {
        ReturnRecord entity = returnRecordRepository.findByDeliveryOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Return record not found for order"));
        return ReturnRecordMapper.toDTO(entity);
    }

    @Override
    public ReturnRecordDTO update(Long id, ReturnRecordDTO dto) {
        ReturnRecord existing = returnRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Return record not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setReason(dto.getReason());
        existing.setStatus(dto.getStatus());
        existing.setRefundProcessed(dto.getRefundProcessed() != null ? dto.getRefundProcessed() : false);
        existing.setRequestedAt(dto.getRequestedAt() != null ? dto.getRequestedAt() : LocalDateTime.now());

        ReturnRecord updated = returnRecordRepository.save(existing);
        return ReturnRecordMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        ReturnRecord existing = returnRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Return record not found"));
        returnRecordRepository.delete(existing);
    }
}
