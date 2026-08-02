package com.example.backend.service.serviceImpl;

import com.example.backend.dto.NotificationDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.Notification;
import com.example.backend.entity.User;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.NotificationMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.NotificationRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public NotificationServiceImpl(
            NotificationRepository notificationRepository,
            UserRepository userRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public NotificationDTO create(NotificationDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DeliveryOrder order = null;
        if (dto.getDeliveryOrderId() != null) {
            order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));
        }

        Notification entity = NotificationMapper.toEntity(dto);
        entity.setUser(user);
        entity.setDeliveryOrder(order);
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }

        Notification saved = notificationRepository.save(entity);
        return NotificationMapper.toDTO(saved);
    }

    @Override
    public List<NotificationDTO> getAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationMapper::toDTO)
                .toList();
    }

    @Override
    public NotificationDTO getById(Long id) {
        Notification entity = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        return NotificationMapper.toDTO(entity);
    }

    @Override
    public List<NotificationDTO> getByUserId(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(NotificationMapper::toDTO)
                .toList();
    }

    @Override
    public List<NotificationDTO> getByDeliveryOrderId(Long orderId) {
        return notificationRepository.findByDeliveryOrderId(orderId).stream()
                .map(NotificationMapper::toDTO)
                .toList();
    }

    @Override
    public NotificationDTO update(Long id, NotificationDTO dto) {
        Notification existing = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DeliveryOrder order = null;
        if (dto.getDeliveryOrderId() != null) {
            order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));
        }

        existing.setUser(user);
        existing.setDeliveryOrder(order);
        existing.setTitle(dto.getTitle());
        existing.setMessage(dto.getMessage());
        existing.setIsRead(dto.getIsRead() != null ? dto.getIsRead() : false);
        existing.setCreatedAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : LocalDateTime.now());

        Notification updated = notificationRepository.save(existing);
        return NotificationMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Notification existing = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notificationRepository.delete(existing);
    }
}
