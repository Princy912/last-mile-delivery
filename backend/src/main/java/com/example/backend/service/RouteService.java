package com.example.backend.service;

import com.example.backend.dto.RouteDTO;
import java.util.List;

public interface RouteService {
    RouteDTO create(RouteDTO dto);
    List<RouteDTO> getAll();
    RouteDTO getById(Long id);
    List<RouteDTO> getByDeliveryAgentId(Long agentId);
    RouteDTO update(Long id, RouteDTO dto);
    void delete(Long id);
}
