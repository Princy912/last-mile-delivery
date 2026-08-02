package com.example.backend.repository;

import com.example.backend.entity.ReturnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReturnRecordRepository extends JpaRepository<ReturnRecord, Long> {
    Optional<ReturnRecord> findByDeliveryOrderId(Long orderId);
}
