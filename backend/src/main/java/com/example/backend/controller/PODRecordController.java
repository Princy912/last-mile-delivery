package com.example.backend.controller;

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
    public ResponseEntity<PODRecord> createPODRecord(@RequestBody PODRecord podRecord) {
        return new ResponseEntity<>(
                podRecordService.createPODRecord(podRecord),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PODRecord>> getAllPODRecords() {
        return ResponseEntity.ok(podRecordService.getAllPODRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PODRecord> getPODRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(podRecordService.getPODRecordById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PODRecord> updatePODRecord(@PathVariable Long id,
                                                     @RequestBody PODRecord podRecord) {
        return ResponseEntity.ok(
                podRecordService.updatePODRecord(id, podRecord));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePODRecord(@PathVariable Long id) {
        podRecordService.deletePODRecord(id);
        return ResponseEntity.ok("POD Record deleted successfully");
    }
}