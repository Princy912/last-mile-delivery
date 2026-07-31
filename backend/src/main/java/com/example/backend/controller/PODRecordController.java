package com.example.backend.controller;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.PODRecord;
import com.example.backend.service.PODRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pod-records")
public class PODRecordController {

    private final PODRecordService podRecordService;

    public PODRecordController(PODRecordService podRecordService) {
        this.podRecordService = podRecordService;
    }

    @PostMapping
    public ResponseEntity<PODRecordDTO> createPODRecord(@RequestBody PODRecord podRecord) {
        return new ResponseEntity<>(
                podRecordService.create(podRecord),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PODRecordDTO>> getAllPODRecords() {
        return ResponseEntity.ok(podRecordService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PODRecordDTO> getPODRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(podRecordService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PODRecordDTO> updatePODRecord(@PathVariable Long id,
                                                        @RequestBody PODRecord podRecord) {
        return ResponseEntity.ok(
                podRecordService.update(id, podRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePODRecord(@PathVariable Long id) {
        podRecordService.delete(id);
        return ResponseEntity.ok("POD Record deleted successfully");
    }
}