package com.example.backend.service.serviceImpl;

import com.example.backend.dto.RouteDTO;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.entity.Route;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.RouteMapper;
import com.example.backend.repository.DeliveryAgentRepository;
import com.example.backend.repository.RouteRepository;
import com.example.backend.service.RouteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    public RouteServiceImpl(RouteRepository routeRepository, DeliveryAgentRepository deliveryAgentRepository) {
        this.routeRepository = routeRepository;
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    @Override
    public RouteDTO create(RouteDTO dto) {
        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));
        Route entity = RouteMapper.toEntity(dto);
        entity.setDeliveryAgent(agent);
        entity.setCreatedAt(LocalDateTime.now());
        Route saved = routeRepository.save(entity);
        return RouteMapper.toDTO(saved);
    }

    @Override
    public List<RouteDTO> getAll() {
        return routeRepository.findAll().stream()
                .map(RouteMapper::toDTO)
                .toList();
    }

    @Override
    public RouteDTO getById(Long id) {
        Route entity = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        return RouteMapper.toDTO(entity);
    }

    @Override
    public List<RouteDTO> getByDeliveryAgentId(Long agentId) {
        return routeRepository.findByDeliveryAgentId(agentId).stream()
                .map(RouteMapper::toDTO)
                .toList();
    }

    @Override
    public RouteDTO update(Long id, RouteDTO dto) {
        Route existing = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        existing.setDeliveryAgent(agent);
        existing.setRouteName(dto.getRouteName());
        existing.setStartLat(dto.getStartLat());
        existing.setStartLng(dto.getStartLng());
        existing.setEndLat(dto.getEndLat());
        existing.setEndLng(dto.getEndLng());
        existing.setStatus(dto.getStatus());

        Route updated = routeRepository.save(existing);
        return RouteMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        Route existing = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        routeRepository.delete(existing);
    }
}
