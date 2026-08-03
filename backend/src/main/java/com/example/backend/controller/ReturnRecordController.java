package com.example.backend.controller;

import com.example.backend.dto.ReturnRecordDTO;
import com.example.backend.service.ReturnRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/returns")
public class ReturnRecordController {

    private final ReturnRecordService returnRecordService;

    public ReturnRecordController(ReturnRecordService returnRecordService) {
        this.returnRecordService = returnRecordService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'ADMIN')")
    public ResponseEntity<ReturnRecordDTO> create(@Valid @RequestBody ReturnRecordDTO dto) {
        return new ResponseEntity<>(returnRecordService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'DELIVERY_AGENT')")
    public ResponseEntity<List<ReturnRecordDTO>> getAll() {
        return ResponseEntity.ok(returnRecordService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'DELIVERY_AGENT', 'CUSTOMER')")
    public ResponseEntity<ReturnRecordDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership check to ensure CUSTOMER can only read their own returns
        return ResponseEntity.ok(returnRecordService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'DISPATCHER', 'DELIVERY_AGENT', 'CUSTOMER')")
    public ResponseEntity<ReturnRecordDTO> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership check to ensure CUSTOMER can only read their own returns
        return ResponseEntity.ok(returnRecordService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERATIONS_MANAGER', 'DISPATCHER', 'ADMIN')")
    public ResponseEntity<ReturnRecordDTO> update(@PathVariable Long id, @Valid @RequestBody ReturnRecordDTO dto) {
        return ResponseEntity.ok(returnRecordService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        returnRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
