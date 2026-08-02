package com.example.backend.service;

import com.example.backend.dto.DeliveryStatusHistoryDTO;
import java.util.List;

public interface DeliveryStatusHistoryService {
    DeliveryStatusHistoryDTO create(DeliveryStatusHistoryDTO dto);
    List<DeliveryStatusHistoryDTO> getAll();
    DeliveryStatusHistoryDTO getById(Long id);
    List<DeliveryStatusHistoryDTO> getByDeliveryOrderId(Long orderId);
    DeliveryStatusHistoryDTO update(Long id, DeliveryStatusHistoryDTO dto);
    void delete(Long id);
}
