package com.example.backend.mapper;

import com.example.backend.dto.FailedDeliveryDTO;
import com.example.backend.entity.FailedDelivery;

public class FailedDeliveryMapper {

    public static FailedDeliveryDTO toDTO(FailedDelivery entity) {
        if (entity == null) {
            return null;
        }
        return FailedDeliveryDTO.builder()
                .id(entity.getId())
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .reason(entity.getReason())
                .notes(entity.getNotes())
                .attemptedTime(entity.getAttemptedTime())
                .build();
    }

    public static FailedDelivery toEntity(FailedDeliveryDTO dto) {
        if (dto == null) {
            return null;
        }
        return FailedDelivery.builder()
                .id(dto.getId())
                .reason(dto.getReason())
                .notes(dto.getNotes())
                .attemptedTime(dto.getAttemptedTime())
                .build();
    }
}
