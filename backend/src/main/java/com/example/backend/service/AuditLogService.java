package com.example.backend.service;

import com.example.backend.dto.AuditLogDTO;
import java.util.List;

public interface AuditLogService {
    AuditLogDTO create(AuditLogDTO dto);
    List<AuditLogDTO> getAll();
    AuditLogDTO getById(Long id);
    List<AuditLogDTO> getByUserId(Long userId);
    AuditLogDTO update(Long id, AuditLogDTO dto);
    void delete(Long id);
}
