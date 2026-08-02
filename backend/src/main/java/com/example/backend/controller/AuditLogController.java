package com.example.backend.controller;

import com.example.backend.dto.AuditLogDTO;
import com.example.backend.service.AuditLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @PostMapping
    public ResponseEntity<AuditLogDTO> create(@Valid @RequestBody AuditLogDTO dto) {
        return new ResponseEntity<>(auditLogService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> getAll() {
        return ResponseEntity.ok(auditLogService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLogDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(auditLogService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLogDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(auditLogService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditLogDTO> update(@PathVariable Long id, @Valid @RequestBody AuditLogDTO dto) {
        return ResponseEntity.ok(auditLogService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        auditLogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
