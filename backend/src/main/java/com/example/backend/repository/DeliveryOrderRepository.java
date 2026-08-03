package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.DeliveryOrder;
import java.util.Optional;
import java.util.List;

public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrder, Long> {
    boolean existsByTrackingNumber(String trackingNumber);
    Optional<DeliveryOrder> findByTrackingNumber(String trackingNumber);
    List<DeliveryOrder> findByDeliveryAgentIsNull();
}