package com.example.backend.mapper;

import com.example.backend.dto.AuditLogDTO;
import com.example.backend.entity.AuditLog;

public class AuditLogMapper {

    public static AuditLogDTO toDTO(AuditLog entity) {
        if (entity == null) {
            return null;
        }
        return AuditLogDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .action(entity.getAction())
                .details(entity.getDetails())
                .ipAddress(entity.getIpAddress())
                .timestamp(entity.getTimestamp())
                .build();
    }

    public static AuditLog toEntity(AuditLogDTO dto) {
        if (dto == null) {
            return null;
        }
        return AuditLog.builder()
                .id(dto.getId())
                .action(dto.getAction())
                .details(dto.getDetails())
                .ipAddress(dto.getIpAddress())
                .timestamp(dto.getTimestamp())
                .build();
    }
}
