package com.example.backend.service.serviceImpl;

import com.example.backend.dto.AuditLogDTO;
import com.example.backend.entity.AuditLog;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.AuditLogMapper;
import com.example.backend.repository.AuditLogRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.AuditLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final UserRepository userRepository;

    public AuditLogServiceImpl(AuditLogRepository auditLogRepository, UserRepository userRepository) {
        this.auditLogRepository = auditLogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AuditLogDTO create(AuditLogDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        AuditLog entity = AuditLogMapper.toEntity(dto);
        entity.setUser(user);
        if (entity.getTimestamp() == null) {
            entity.setTimestamp(LocalDateTime.now());
        }

        AuditLog saved = auditLogRepository.save(entity);
        return AuditLogMapper.toDTO(saved);
    }

    @Override
    public List<AuditLogDTO> getAll() {
        return auditLogRepository.findAll().stream()
                .map(AuditLogMapper::toDTO)
                .toList();
    }

    @Override
    public AuditLogDTO getById(Long id) {
        AuditLog entity = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit Log record not found"));
        return AuditLogMapper.toDTO(entity);
    }

    @Override
    public List<AuditLogDTO> getByUserId(Long userId) {
        return auditLogRepository.findByUserId(userId).stream()
                .map(AuditLogMapper::toDTO)
                .toList();
    }

    @Override
    public AuditLogDTO update(Long id, AuditLogDTO dto) {
        AuditLog existing = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit Log record not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setUser(user);
        existing.setAction(dto.getAction());
        existing.setDetails(dto.getDetails());
        existing.setIpAddress(dto.getIpAddress());
        existing.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        AuditLog updated = auditLogRepository.save(existing);
        return AuditLogMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        AuditLog existing = auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit Log record not found"));
        auditLogRepository.delete(existing);
    }
}
