package com.example.backend.mapper;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.entity.Notification;

public class NotificationMapper {

    public static NotificationDTO toDTO(Notification entity) {
        if (entity == null) {
            return null;
        }
        return NotificationDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .title(entity.getTitle())
                .message(entity.getMessage())
                .isRead(entity.getIsRead())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }
        return Notification.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .message(dto.getMessage())
                .isRead(dto.getIsRead() != null ? dto.getIsRead() : false)
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
