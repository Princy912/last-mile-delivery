package com.example.backend.mapper;

import com.example.backend.dto.PODRecordDTO;
import com.example.backend.entity.PODRecord;

public class PODRecordMapper {

    public static PODRecordDTO toDTO(PODRecord podRecord) {

        if (podRecord == null) {
            return null;
        }

        return PODRecordDTO.builder()
                .id(podRecord.getId())
                .deliveryOrderId(podRecord.getDeliveryOrder().getId())
                .trackingNumber(podRecord.getDeliveryOrder().getTrackingNumber())
                .podType(podRecord.getPodType())
                .podData(podRecord.getPodData())
                .capturedAt(podRecord.getCapturedAt())
                .build();
    }
}