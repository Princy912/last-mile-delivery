package com.example.backend.controller;

import com.example.backend.dto.RouteDTO;
import com.example.backend.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<RouteDTO> create(@Valid @RequestBody RouteDTO dto) {
        return new ResponseEntity<>(routeService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<RouteDTO>> getAll() {
        return ResponseEntity.ok(routeService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<RouteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<RouteDTO>> getByDeliveryAgentId(@PathVariable Long agentId) {
        return ResponseEntity.ok(routeService.getByDeliveryAgentId(agentId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<RouteDTO> update(@PathVariable Long id, @Valid @RequestBody RouteDTO dto) {
        return ResponseEntity.ok(routeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
