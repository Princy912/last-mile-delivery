package com.example.backend.service.serviceImpl;

import com.example.backend.dto.OTPVerificationDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.OTPVerification;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.OTPVerificationMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.OTPVerificationRepository;
import com.example.backend.service.OTPVerificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OTPVerificationServiceImpl implements OTPVerificationService {

    private final OTPVerificationRepository otpVerificationRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public OTPVerificationServiceImpl(
            OTPVerificationRepository otpVerificationRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.otpVerificationRepository = otpVerificationRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public OTPVerificationDTO create(OTPVerificationDTO dto) {
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        OTPVerification entity = OTPVerificationMapper.toEntity(dto);
        entity.setDeliveryOrder(order);

        OTPVerification saved = otpVerificationRepository.save(entity);
        return OTPVerificationMapper.toDTO(saved);
    }

    @Override
    public List<OTPVerificationDTO> getAll() {
        return otpVerificationRepository.findAll().stream()
                .map(OTPVerificationMapper::toDTO)
                .toList();
    }

    @Override
    public OTPVerificationDTO getById(Long id) {
        OTPVerification entity = otpVerificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OTP Verification record not found"));
        return OTPVerificationMapper.toDTO(entity);
    }

    @Override
    public OTPVerificationDTO getByDeliveryOrderId(Long orderId) {
        OTPVerification entity = otpVerificationRepository.findByDeliveryOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("OTP Verification record not found for order"));
        return OTPVerificationMapper.toDTO(entity);
    }

    @Override
    public OTPVerificationDTO update(Long id, OTPVerificationDTO dto) {
        OTPVerification existing = otpVerificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OTP Verification record not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        existing.setDeliveryOrder(order);
        existing.setOtpCode(dto.getOtpCode());
        existing.setIsVerified(dto.getIsVerified() != null ? dto.getIsVerified() : false);
        existing.setExpiredAt(dto.getExpiredAt());
        existing.setVerifiedAt(dto.getVerifiedAt());

        OTPVerification updated = otpVerificationRepository.save(existing);
        return OTPVerificationMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        OTPVerification existing = otpVerificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OTP Verification record not found"));
        otpVerificationRepository.delete(existing);
    }
}
