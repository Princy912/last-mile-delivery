package com.example.backend.service;

import java.util.List;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.entity.DeliveryAgent;

public interface DeliveryAgentService {

    DeliveryAgentDTO create(DeliveryAgent deliveryAgent);

    List<DeliveryAgentDTO> getAll();

    DeliveryAgentDTO getById(Long id);

    DeliveryAgentDTO update(Long id, DeliveryAgent deliveryAgent);

    void delete(Long id);
}