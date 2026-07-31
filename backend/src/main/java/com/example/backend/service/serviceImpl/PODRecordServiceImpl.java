package com.example.backend.service.serviceImpl;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.PODRecord;
import com.example.backend.mapper.PODRecordMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.PODRecordRepository;
import com.example.backend.service.PODRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PODRecordServiceImpl implements PODRecordService {

    private final PODRecordRepository podRecordRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public PODRecordServiceImpl(PODRecordRepository podRecordRepository,
                                DeliveryOrderRepository deliveryOrderRepository) {
        this.podRecordRepository = podRecordRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public PODRecordDTO create(PODRecord podRecord) {

        DeliveryOrder order = deliveryOrderRepository.findById(
                podRecord.getDeliveryOrder().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        podRecord.setDeliveryOrder(order);
        podRecord.setCapturedAt(LocalDateTime.now());

        PODRecord savedPOD = podRecordRepository.save(podRecord);
        return PODRecordMapper.toDTO(savedPOD);
    }

    @Override
    public List<PODRecordDTO> getAll() {
        return podRecordRepository.findAll()
                .stream()
                .map(PODRecordMapper::toDTO)
                .toList();
    }

    @Override
    public PODRecordDTO getById(Long id) {
        PODRecord podRecord = podRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POD Record not found"));
        return PODRecordMapper.toDTO(podRecord);
    }

    @Override
    public PODRecordDTO update(Long id, PODRecord podRecord) {

        PODRecord existing = podRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POD Record not found"));

        DeliveryOrder order = deliveryOrderRepository.findById(
                podRecord.getDeliveryOrder().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setPodType(podRecord.getPodType());
        existing.setPodData(podRecord.getPodData());

        PODRecord updatedPOD = podRecordRepository.save(existing);
        return PODRecordMapper.toDTO(updatedPOD);
    }

    @Override
    public void delete(Long id) {
        podRecordRepository.deleteById(id);
    }
}