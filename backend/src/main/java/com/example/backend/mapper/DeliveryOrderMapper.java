package com.example.backend.mapper;

import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.entity.DeliveryOrder;

public class DeliveryOrderMapper {

    public static DeliveryOrderDTO toDTO(DeliveryOrder order) {

        if (order == null) {
            return null;
        }

        return DeliveryOrderDTO.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getName())
                .deliveryAgentId(order.getDeliveryAgent().getId())
                .deliveryAgentName(order.getDeliveryAgent().getUser().getName())
                .trackingNumber(order.getTrackingNumber())
                .pickupAddress(order.getPickupAddress())
                .deliveryAddress(order.getDeliveryAddress())
                .status(order.getStatus())
                .priority(order.getPriority())
                .estimatedDeliveryTime(order.getEstimatedDeliveryTime())
                .actualDeliveryTime(order.getActualDeliveryTime())
                .createdAt(order.getCreatedAt())
                .build();
    }
}