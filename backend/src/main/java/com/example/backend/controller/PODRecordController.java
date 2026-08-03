package com.example.backend.controller;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.PODRecord;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.service.PODRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/pod-records")
public class PODRecordController {

    private final PODRecordService podRecordService;

    public PODRecordController(PODRecordService podRecordService) {
        this.podRecordService = podRecordService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<PODRecordDTO> createPODRecord(@Valid @RequestBody PODRecordDTO dto) {
        PODRecord podRecord = new PODRecord();
        podRecord.setDeliveryOrder(DeliveryOrder.builder().id(dto.getDeliveryOrderId()).build());
        podRecord.setPodType(dto.getPodType());
        podRecord.setPodData(dto.getPodData());
        return new ResponseEntity<>(
                podRecordService.create(podRecord),
                HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER')")
    public ResponseEntity<List<PODRecordDTO>> getAllPODRecords() {
        return ResponseEntity.ok(podRecordService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'CUSTOMER')")
    public ResponseEntity<PODRecordDTO> getPODRecordById(@PathVariable Long id) {
        // TODO: Implement ownership check to ensure CUSTOMER can only read POD for their own orders
        return ResponseEntity.ok(podRecordService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<PODRecordDTO> updatePODRecord(@PathVariable Long id,
                                                        @Valid @RequestBody PODRecordDTO dto) {
        // TODO: Implement ownership check to ensure DELIVERY_AGENT can only update POD they created / for their assigned delivery
        PODRecord podRecord = new PODRecord();
        podRecord.setDeliveryOrder(DeliveryOrder.builder().id(dto.getDeliveryOrderId()).build());
        podRecord.setPodType(dto.getPodType());
        podRecord.setPodData(dto.getPodData());
        return ResponseEntity.ok(
                podRecordService.update(id, podRecord));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePODRecord(@PathVariable Long id) {
        podRecordService.delete(id);
        return ResponseEntity.ok("POD Record deleted successfully");
    }
}