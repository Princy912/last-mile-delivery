package com.example.backend.repository;

import com.example.backend.entity.OTPVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPVerificationRepository extends JpaRepository<OTPVerification, Long> {
    Optional<OTPVerification> findByDeliveryOrderId(Long orderId);
}
