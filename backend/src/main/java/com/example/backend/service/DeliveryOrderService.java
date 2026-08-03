package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.DeliveryOrderDTO;
import com.example.backend.entity.DeliveryOrder;

public interface DeliveryOrderService {

    DeliveryOrderDTO create(DeliveryOrder deliveryOrder);

    List<DeliveryOrderDTO> getAll();

    DeliveryOrderDTO getById(Long id);

    DeliveryOrderDTO update(Long id, DeliveryOrder deliveryOrder);

    void delete(Long id);

    List<DeliveryOrderDTO> getUnassignedOrders();

    DeliveryOrderDTO assignOrder(Long id, Long deliveryAgentId);

    DeliveryOrderDTO updateOrderStatus(Long id, com.example.backend.enums.OrderStatus status);
}