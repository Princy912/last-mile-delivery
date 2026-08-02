package com.example.backend.mapper;

import com.example.backend.dto.OTPVerificationDTO;
import com.example.backend.entity.OTPVerification;

public class OTPVerificationMapper {

    public static OTPVerificationDTO toDTO(OTPVerification entity) {
        if (entity == null) {
            return null;
        }
        return OTPVerificationDTO.builder()
                .id(entity.getId())
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .otpCode(entity.getOtpCode())
                .isVerified(entity.getIsVerified())
                .expiredAt(entity.getExpiredAt())
                .verifiedAt(entity.getVerifiedAt())
                .build();
    }

    public static OTPVerification toEntity(OTPVerificationDTO dto) {
        if (dto == null) {
            return null;
        }
        return OTPVerification.builder()
                .id(dto.getId())
                .otpCode(dto.getOtpCode())
                .isVerified(dto.getIsVerified() != null ? dto.getIsVerified() : false)
                .expiredAt(dto.getExpiredAt())
                .verifiedAt(dto.getVerifiedAt())
                .build();
    }
}
