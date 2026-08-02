package com.example.backend.mapper;

import com.example.backend.dto.RouteDTO;
import com.example.backend.entity.Route;

public class RouteMapper {

    public static RouteDTO toDTO(Route entity) {
        if (entity == null) {
            return null;
        }
        return RouteDTO.builder()
                .id(entity.getId())
                .deliveryAgentId(entity.getDeliveryAgent() != null ? entity.getDeliveryAgent().getId() : null)
                .routeName(entity.getRouteName())
                .startLat(entity.getStartLat())
                .startLng(entity.getStartLng())
                .endLat(entity.getEndLat())
                .endLng(entity.getEndLng())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static Route toEntity(RouteDTO dto) {
        if (dto == null) {
            return null;
        }
        return Route.builder()
                .id(dto.getId())
                .routeName(dto.getRouteName())
                .startLat(dto.getStartLat())
                .startLng(dto.getStartLng())
                .endLat(dto.getEndLat())
                .endLng(dto.getEndLng())
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt())
                .build();
    }
}
