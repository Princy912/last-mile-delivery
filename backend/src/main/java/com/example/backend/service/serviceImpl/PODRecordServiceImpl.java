package com.example.backend.service.serviceImpl;

import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.PODRecord;
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
    public PODRecord createPODRecord(PODRecord podRecord) {

        DeliveryOrder order = deliveryOrderRepository.findById(
                podRecord.getDeliveryOrder().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        podRecord.setDeliveryOrder(order);
        podRecord.setCapturedAt(LocalDateTime.now());

        return podRecordRepository.save(podRecord);
    }

    @Override
    public List<PODRecord> getAllPODRecords() {
        return podRecordRepository.findAll();
    }

    @Override
    public PODRecord getPODRecordById(Long id) {
        return podRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("POD Record not found"));
    }

    @Override
    public PODRecord updatePODRecord(Long id, PODRecord podRecord) {

        PODRecord existing = getPODRecordById(id);

        DeliveryOrder order = deliveryOrderRepository.findById(
                podRecord.getDeliveryOrder().getId())
                .orElseThrow(() -> new RuntimeException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setPodType(podRecord.getPodType());
        existing.setPodData(podRecord.getPodData());

        return podRecordRepository.save(existing);
    }

    @Override
    public void deletePODRecord(Long id) {
        podRecordRepository.deleteById(id);
    }
}