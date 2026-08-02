package com.example.backend.service;

import com.example.backend.dto.OTPVerificationDTO;
import java.util.List;

public interface OTPVerificationService {
    OTPVerificationDTO create(OTPVerificationDTO dto);
    List<OTPVerificationDTO> getAll();
    OTPVerificationDTO getById(Long id);
    OTPVerificationDTO getByDeliveryOrderId(Long orderId);
    OTPVerificationDTO update(Long id, OTPVerificationDTO dto);
    void delete(Long id);
}
