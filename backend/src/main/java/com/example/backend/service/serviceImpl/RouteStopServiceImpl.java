package com.example.backend.service.serviceImpl;

import com.example.backend.dto.RouteStopDTO;
import com.example.backend.entity.DeliveryOrder;
import com.example.backend.entity.Route;
import com.example.backend.entity.RouteStop;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.RouteStopMapper;
import com.example.backend.repository.DeliveryOrderRepository;
import com.example.backend.repository.RouteRepository;
import com.example.backend.repository.RouteStopRepository;
import com.example.backend.service.RouteStopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteStopServiceImpl implements RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final RouteRepository routeRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    public RouteStopServiceImpl(
            RouteStopRepository routeStopRepository,
            RouteRepository routeRepository,
            DeliveryOrderRepository deliveryOrderRepository
    ) {
        this.routeStopRepository = routeStopRepository;
        this.routeRepository = routeRepository;
        this.deliveryOrderRepository = deliveryOrderRepository;
    }

    @Override
    public RouteStopDTO create(RouteStopDTO dto) {
        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        RouteStop entity = RouteStopMapper.toEntity(dto);
        entity.setRoute(route);
        entity.setDeliveryOrder(order);

        RouteStop saved = routeStopRepository.save(entity);
        return RouteStopMapper.toDTO(saved);
    }

    @Override
    public List<RouteStopDTO> getAll() {
        return routeStopRepository.findAll().stream()
                .map(RouteStopMapper::toDTO)
                .toList();
    }

    @Override
    public RouteStopDTO getById(Long id) {
        RouteStop entity = routeStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route Stop not found"));
        return RouteStopMapper.toDTO(entity);
    }

    @Override
    public List<RouteStopDTO> getByRouteId(Long routeId) {
        return routeStopRepository.findByRouteId(routeId).stream()
                .map(RouteStopMapper::toDTO)
                .toList();
    }

    @Override
    public RouteStopDTO getByDeliveryOrderId(Long orderId) {
        RouteStop entity = routeStopRepository.findByDeliveryOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Route Stop not found for order"));
        return RouteStopMapper.toDTO(entity);
    }

    @Override
    public RouteStopDTO update(Long id, RouteStopDTO dto) {
        RouteStop existing = routeStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route Stop not found"));
        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        DeliveryOrder order = deliveryOrderRepository.findById(dto.getDeliveryOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Order not found"));

        existing.setRoute(route);
        existing.setDeliveryOrder(order);
        existing.setSequence(dto.getSequence());
        existing.setStatus(dto.getStatus());
        existing.setEstimatedArrivalTime(dto.getEstimatedArrivalTime());
        existing.setActualArrivalTime(dto.getActualArrivalTime());

        RouteStop updated = routeStopRepository.save(existing);
        return RouteStopMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        RouteStop existing = routeStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route Stop not found"));
        routeStopRepository.delete(existing);
    }
}
