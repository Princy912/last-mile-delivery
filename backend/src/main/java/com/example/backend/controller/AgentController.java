package com.example.backend.controller;

import com.example.backend.dto.DeliveryAgentDTO;
import com.example.backend.dto.DeliveryTrackingDTO;
import com.example.backend.dto.LiveAgentDTO;
import com.example.backend.service.DeliveryAgentService;
import com.example.backend.service.DeliveryTrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final DeliveryTrackingService deliveryTrackingService;
    private final DeliveryAgentService deliveryAgentService;

    public AgentController(
            DeliveryTrackingService deliveryTrackingService,
            DeliveryAgentService deliveryAgentService
    ) {
        this.deliveryTrackingService = deliveryTrackingService;
        this.deliveryAgentService = deliveryAgentService;
    }

    @GetMapping("/live")
    @PreAuthorize("hasAnyRole('ADMIN', 'DISPATCHER', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<LiveAgentDTO>> getLiveAgents() {
        List<DeliveryTrackingDTO> allTracking = deliveryTrackingService.getAll();

        Map<Long, DeliveryTrackingDTO> latestTrackingMap = allTracking.stream()
                .filter(t -> t.getDeliveryAgentId() != null && t.getUpdatedTime() != null)
                .collect(Collectors.toMap(
                        DeliveryTrackingDTO::getDeliveryAgentId,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(DeliveryTrackingDTO::getUpdatedTime))
                ));

        List<LiveAgentDTO> liveAgents = new ArrayList<>();
        for (Map.Entry<Long, DeliveryTrackingDTO> entry : latestTrackingMap.entrySet()) {
            try {
                DeliveryAgentDTO agent = deliveryAgentService.getById(entry.getKey());
                liveAgents.add(LiveAgentDTO.builder()
                        .agentName(agent.getUserName())
                        .latitude(entry.getValue().getLatitude())
                        .longitude(entry.getValue().getLongitude())
                        .vehicleType(agent.getVehicleType())
                        .currentStatus(agent.getStatus().name())
                        .lastUpdated(entry.getValue().getUpdatedTime())
                        .build());
            } catch (Exception e) {
                // Ignore if the agent is not found or details cannot be resolved
            }
        }

        return ResponseEntity.ok(liveAgents);
    }
}
