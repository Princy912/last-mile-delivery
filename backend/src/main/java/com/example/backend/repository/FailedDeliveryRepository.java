package com.example.backend.repository;

import com.example.backend.entity.FailedDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FailedDeliveryRepository extends JpaRepository<FailedDelivery, Long> {
    List<FailedDelivery> findByDeliveryOrderId(Long orderId);
}
