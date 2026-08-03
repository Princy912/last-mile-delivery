package com.example.backend.controller;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.PODRecord;
import com.example.backend.service.PODRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pod")
public class PODController {

    private final PODRecordService podRecordService;

    public PODController(PODRecordService podRecordService) {
        this.podRecordService = podRecordService;
    }

    @PostMapping("/capture")
    @PreAuthorize("hasAnyRole('ADMIN', 'DELIVERY_AGENT')")
    public ResponseEntity<PODRecordDTO> capturePOD(@Valid @RequestBody PODRecordDTO dto) {
        PODRecord podRecord = new PODRecord();
        podRecord.setDeliveryOrder(DeliveryOrder.builder().id(dto.getDeliveryOrderId()).build());
        podRecord.setPodType(dto.getPodType());
        podRecord.setPodData(dto.getPodData());
        return new ResponseEntity<>(podRecordService.create(podRecord), HttpStatus.CREATED);
    }
}
