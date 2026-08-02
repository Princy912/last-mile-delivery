package com.example.backend.mapper;

import com.example.backend.dto.ReturnRecordDTO;
import com.example.backend.entity.ReturnRecord;

public class ReturnRecordMapper {

    public static ReturnRecordDTO toDTO(ReturnRecord entity) {
        if (entity == null) {
            return null;
        }
        return ReturnRecordDTO.builder()
                .id(entity.getId())
                .deliveryOrderId(entity.getDeliveryOrder() != null ? entity.getDeliveryOrder().getId() : null)
                .reason(entity.getReason())
                .status(entity.getStatus())
                .refundProcessed(entity.getRefundProcessed())
                .requestedAt(entity.getRequestedAt())
                .build();
    }

    public static ReturnRecord toEntity(ReturnRecordDTO dto) {
        if (dto == null) {
            return null;
        }
        return ReturnRecord.builder()
                .id(dto.getId())
                .reason(dto.getReason())
                .status(dto.getStatus())
                .refundProcessed(dto.getRefundProcessed() != null ? dto.getRefundProcessed() : false)
                .requestedAt(dto.getRequestedAt())
                .build();
    }
}
