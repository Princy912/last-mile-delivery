package com.example.backend.mapper;

import com.example.backend.dto.DeliveryAddressDTO;
import com.example.backend.entity.DeliveryAddress;
import com.example.backend.entity.User;

public class DeliveryAddressMapper {

    public static DeliveryAddressDTO toDTO(DeliveryAddress entity) {
        if (entity == null) {
            return null;
        }
        return DeliveryAddressDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .addressLine1(entity.getAddressLine1())
                .addressLine2(entity.getAddressLine2())
                .city(entity.getCity())
                .state(entity.getState())
                .postalCode(entity.getPostalCode())
                .country(entity.getCountry())
                .isDefault(entity.getIsDefault())
                .build();
    }

    public static DeliveryAddress toEntity(DeliveryAddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return DeliveryAddress.builder()
                .id(dto.getId())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .isDefault(dto.getIsDefault() != null ? dto.getIsDefault() : false)
                .build();
    }
}
