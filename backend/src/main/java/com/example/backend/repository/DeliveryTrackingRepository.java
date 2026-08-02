package com.example.backend.repository;

import com.example.backend.entity.DeliveryTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryTrackingRepository extends JpaRepository<DeliveryTracking, Long> {
    List<DeliveryTracking> findByDeliveryOrderId(Long orderId);
    List<DeliveryTracking> findByDeliveryAgentId(Long agentId);
}
