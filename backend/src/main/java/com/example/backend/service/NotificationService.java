package com.example.backend.service;

import com.example.backend.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {
    NotificationDTO create(NotificationDTO dto);
    List<NotificationDTO> getAll();
    NotificationDTO getById(Long id);
    List<NotificationDTO> getByUserId(Long userId);
    List<NotificationDTO> getByDeliveryOrderId(Long orderId);
    NotificationDTO update(Long id, NotificationDTO dto);
    void delete(Long id);
}
