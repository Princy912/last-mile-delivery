package com.example.backend.service;

import com.example.backend.dto.RouteStopDTO;
import java.util.List;

public interface RouteStopService {
    RouteStopDTO create(RouteStopDTO dto);
    List<RouteStopDTO> getAll();
    RouteStopDTO getById(Long id);
    List<RouteStopDTO> getByRouteId(Long routeId);
    RouteStopDTO getByDeliveryOrderId(Long orderId);
    RouteStopDTO update(Long id, RouteStopDTO dto);
    void delete(Long id);
}
