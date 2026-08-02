package com.example.backend.controller;

import com.example.backend.dto.ReturnRecordDTO;
import com.example.backend.service.ReturnRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class ReturnRecordController {

    private final ReturnRecordService returnRecordService;

    public ReturnRecordController(ReturnRecordService returnRecordService) {
        this.returnRecordService = returnRecordService;
    }

    @PostMapping
    public ResponseEntity<ReturnRecordDTO> create(@Valid @RequestBody ReturnRecordDTO dto) {
        return new ResponseEntity<>(returnRecordService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReturnRecordDTO>> getAll() {
        return ResponseEntity.ok(returnRecordService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnRecordDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(returnRecordService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ReturnRecordDTO> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(returnRecordService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnRecordDTO> update(@PathVariable Long id, @Valid @RequestBody ReturnRecordDTO dto) {
        return ResponseEntity.ok(returnRecordService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        returnRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
