package com.example.backend.mapper;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.entity.DeliveryAgent;

public class DeliveryAgentMapper {

    public static DeliveryAgentDTO toDTO(DeliveryAgent agent) {

        if (agent == null) {
            return null;
        }

        return DeliveryAgentDTO.builder()
                .id(agent.getId())
                .userId(agent.getUser().getId())
                .userName(agent.getUser().getName())
                .vehicleType(agent.getVehicleType())
                .currentLat(agent.getCurrentLat())
                .currentLng(agent.getCurrentLng())
                .status(agent.getStatus())
                .rating(agent.getRating())
                .build();
    }
}