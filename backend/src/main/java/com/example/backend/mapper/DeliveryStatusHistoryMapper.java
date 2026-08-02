package com.example.backend.mapper;

import com.example.backend.dto.DeliveryStatusHistoryDTO;
import com.example.backend.entity.DeliveryStatusHistory;

public class DeliveryStatusHistoryMapper {

    public static DeliveryStatusHistoryDTO toDTO(DeliveryStatusHistory entity) {
        if (entity == null) {
            return null;
        }
        return DeliveryStatusHistoryDTO.builder()
                .id(entity.getId())
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .status(entity.getStatus())
                .comments(entity.getComments())
                .updatedTime(entity.getUpdatedTime())
                .build();
    }

    public static DeliveryStatusHistory toEntity(DeliveryStatusHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        return DeliveryStatusHistory.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .comments(dto.getComments())
                .updatedTime(dto.getUpdatedTime())
                .build();
    }
}
