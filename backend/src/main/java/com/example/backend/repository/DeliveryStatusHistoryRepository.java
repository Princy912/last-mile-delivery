package com.example.backend.repository;

import com.example.backend.entity.DeliveryStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryStatusHistoryRepository extends JpaRepository<DeliveryStatusHistory, Long> {
    List<DeliveryStatusHistory> findByDeliveryOrderId(Long orderId);
}
