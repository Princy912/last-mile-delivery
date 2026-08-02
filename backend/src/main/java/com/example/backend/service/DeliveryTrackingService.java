package com.example.backend.service;

import com.example.backend.dto.DeliveryTrackingDTO;
import java.util.List;

public interface DeliveryTrackingService {
    DeliveryTrackingDTO create(DeliveryTrackingDTO dto);
    List<DeliveryTrackingDTO> getAll();
    DeliveryTrackingDTO getById(Long id);
    List<DeliveryTrackingDTO> getByDeliveryOrderId(Long orderId);
    List<DeliveryTrackingDTO> getByDeliveryAgentId(Long agentId);
    DeliveryTrackingDTO update(Long id, DeliveryTrackingDTO dto);
    void delete(Long id);
}
