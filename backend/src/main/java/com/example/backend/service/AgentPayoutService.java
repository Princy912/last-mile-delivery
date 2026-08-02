package com.example.backend.service;

import com.example.backend.dto.AgentPayoutDTO;
import java.util.List;

public interface AgentPayoutService {
    AgentPayoutDTO create(AgentPayoutDTO dto);
    List<AgentPayoutDTO> getAll();
    AgentPayoutDTO getById(Long id);
    List<AgentPayoutDTO> getByDeliveryAgentId(Long agentId);
    AgentPayoutDTO update(Long id, AgentPayoutDTO dto);
    void delete(Long id);
}
