package com.example.backend.service;

import com.example.backend.dto.DeliveryAddressDTO;
import java.util.List;

public interface DeliveryAddressService {
    DeliveryAddressDTO create(DeliveryAddressDTO dto);
    List<DeliveryAddressDTO> getAll();
    DeliveryAddressDTO getById(Long id);
    List<DeliveryAddressDTO> getByUserId(Long userId);
    DeliveryAddressDTO update(Long id, DeliveryAddressDTO dto);
    void delete(Long id);
}
