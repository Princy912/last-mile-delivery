package com.example.backend.mapper;

import com.example.backend.dto.RouteStopDTO;
import com.example.backend.entity.RouteStop;

public class RouteStopMapper {

    public static RouteStopDTO toDTO(RouteStop entity) {
        if (entity == null) {
            return null;
        }
        return RouteStopDTO.builder()
                .id(entity.getId())
                .routeId(entity.getRoute() != null ? entity.getRoute().getId() : null)
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .sequence(entity.getSequence())
                .status(entity.getStatus())
                .estimatedArrivalTime(entity.getEstimatedArrivalTime())
                .actualArrivalTime(entity.getActualArrivalTime())
                .build();
    }

    public static RouteStop toEntity(RouteStopDTO dto) {
        if (dto == null) {
            return null;
        }
        return RouteStop.builder()
                .id(dto.getId())
                .sequence(dto.getSequence())
                .status(dto.getStatus())
                .estimatedArrivalTime(dto.getEstimatedArrivalTime())
                .actualArrivalTime(dto.getActualArrivalTime())
                .build();
    }
}
