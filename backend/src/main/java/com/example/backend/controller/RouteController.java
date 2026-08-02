package com.example.backend.controller;

import com.example.backend.dto.RouteDTO;
import com.example.backend.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public ResponseEntity<RouteDTO> create(@Valid @RequestBody RouteDTO dto) {
        return new ResponseEntity<>(routeService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RouteDTO>> getAll() {
        return ResponseEntity.ok(routeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<RouteDTO>> getByDeliveryAgentId(@PathVariable Long agentId) {
        return ResponseEntity.ok(routeService.getByDeliveryAgentId(agentId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteDTO> update(@PathVariable Long id, @Valid @RequestBody RouteDTO dto) {
        return ResponseEntity.ok(routeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
