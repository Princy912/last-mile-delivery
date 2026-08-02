package com.example.backend.service;

import com.example.backend.dto.FailedDeliveryDTO;
import java.util.List;

public interface FailedDeliveryService {
    FailedDeliveryDTO create(FailedDeliveryDTO dto);
    List<FailedDeliveryDTO> getAll();
    FailedDeliveryDTO getById(Long id);
    List<FailedDeliveryDTO> getByDeliveryOrderId(Long orderId);
    FailedDeliveryDTO update(Long id, FailedDeliveryDTO dto);
    void delete(Long id);
}
