package com.example.backend.controller;

import com.example.backend.dto.RouteStopDTO;
import com.example.backend.service.RouteStopService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route-stops")
public class RouteStopController {

    private final RouteStopService routeStopService;

    public RouteStopController(RouteStopService routeStopService) {
        this.routeStopService = routeStopService;
    }

    @PostMapping
    public ResponseEntity<RouteStopDTO> create(@Valid @RequestBody RouteStopDTO dto) {
        return new ResponseEntity<>(routeStopService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RouteStopDTO>> getAll() {
        return ResponseEntity.ok(routeStopService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteStopDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(routeStopService.getById(id));
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<RouteStopDTO>> getByRouteId(@PathVariable Long routeId) {
        return ResponseEntity.ok(routeStopService.getByRouteId(routeId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<RouteStopDTO> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(routeStopService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteStopDTO> update(@PathVariable Long id, @Valid @RequestBody RouteStopDTO dto) {
        return ResponseEntity.ok(routeStopService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        routeStopService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
