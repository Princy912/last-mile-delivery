package com.example.backend.service;

import java.util.List;

import com.example.backend.entity.DeliveryOrder;

public interface DeliveryOrderService {

    DeliveryOrder createDeliveryOrder(DeliveryOrder deliveryOrder);

    List<DeliveryOrder> getAllDeliveryOrders();

    DeliveryOrder getDeliveryOrderById(Long id);

    DeliveryOrder updateDeliveryOrder(Long id, DeliveryOrder deliveryOrder);

    void deleteDeliveryOrder(Long id);
}