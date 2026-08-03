package com.example.backend.controller;

import com.example.backend.dto.AgentPayoutDTO;
import com.example.backend.service.AgentPayoutService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/agent-payouts")
public class AgentPayoutController {

    private final AgentPayoutService agentPayoutService;

    public AgentPayoutController(AgentPayoutService agentPayoutService) {
        this.agentPayoutService = agentPayoutService;
    }

    @PostMapping
    @PreAuthorize("hasRole('FINANCE')")
    public ResponseEntity<AgentPayoutDTO> create(@Valid @RequestBody AgentPayoutDTO dto) {
        return new ResponseEntity<>(agentPayoutService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE')")
    public ResponseEntity<List<AgentPayoutDTO>> getAll() {
        return ResponseEntity.ok(agentPayoutService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE', 'DELIVERY_AGENT')")
    public ResponseEntity<AgentPayoutDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure DELIVERY_AGENT can only read their own payouts
        return ResponseEntity.ok(agentPayoutService.getById(id));
    }

    @GetMapping("/agent/{agentId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FINANCE', 'DELIVERY_AGENT')")
    public ResponseEntity<List<AgentPayoutDTO>> getByDeliveryAgentId(@PathVariable Long agentId) {
        // TODO: Implement ownership validation to ensure DELIVERY_AGENT can only read their own payouts
        return ResponseEntity.ok(agentPayoutService.getByDeliveryAgentId(agentId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('FINANCE')")
    public ResponseEntity<AgentPayoutDTO> update(@PathVariable Long id, @Valid @RequestBody AgentPayoutDTO dto) {
        return ResponseEntity.ok(agentPayoutService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FINANCE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        agentPayoutService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
