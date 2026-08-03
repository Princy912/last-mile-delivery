package com.example.backend.mapper;

import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.entity.DeliveryTracking;
import com.example.backend.enums.TrackingStatus;

public class DeliveryTrackingMapper {

    public static DeliveryTrackingDTO toDTO(DeliveryTracking entity) {
        if (entity == null) {
            return null;
        }
        return DeliveryTrackingDTO.builder()
                .id(entity.getId())
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .deliveryAgentId(entity.getDeliveryAgent() != null ? entity.getDeliveryAgent().getId() : null)
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .updatedTime(entity.getUpdatedTime())
                .status(entity.getStatus())
                .build();
    }

    public static DeliveryTracking toEntity(DeliveryTrackingDTO dto) {
        if (dto == null) {
            return null;
        }
        return DeliveryTracking.builder()
                .id(dto.getId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .updatedTime(dto.getUpdatedTime())
                .status(dto.getStatus())
                .build();
    }
}
