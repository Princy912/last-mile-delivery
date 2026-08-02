package com.example.backend.mapper;

import com.example.backend.dto.AgentPayoutDTO;
import com.example.backend.entity.AgentPayout;

public class AgentPayoutMapper {

    public static AgentPayoutDTO toDTO(AgentPayout entity) {
        if (entity == null) {
            return null;
        }
        return AgentPayoutDTO.builder()
                .id(entity.getId())
                .deliveryAgentId(entity.getDeliveryAgent() != null ? entity.getDeliveryAgent().getId() : null)
                .amount(entity.getAmount())
                .status(entity.getStatus())
                .transactionReference(entity.getTransactionReference())
                .paidAt(entity.getPaidAt())
                .build();
    }

    public static AgentPayout toEntity(AgentPayoutDTO dto) {
        if (dto == null) {
            return null;
        }
        return AgentPayout.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .status(dto.getStatus())
                .transactionReference(dto.getTransactionReference())
                .paidAt(dto.getPaidAt())
                .build();
    }
}
