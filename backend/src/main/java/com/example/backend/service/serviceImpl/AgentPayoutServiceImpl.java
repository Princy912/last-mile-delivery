package com.example.backend.service.serviceImpl;

import com.example.backend.dto.AgentPayoutDTO;
import com.example.backend.entity.AgentPayout;
import com.example.backend.entity.DeliveryAgent;
import com.example.backend.enums.PayoutStatus;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.mapper.AgentPayoutMapper;
import com.example.backend.repository.AgentPayoutRepository;
import com.example.backend.repository.DeliveryAgentRepository;
import com.example.backend.service.AgentPayoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgentPayoutServiceImpl implements AgentPayoutService {

    private final AgentPayoutRepository agentPayoutRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    public AgentPayoutServiceImpl(
            AgentPayoutRepository agentPayoutRepository,
            DeliveryAgentRepository deliveryAgentRepository
    ) {
        this.agentPayoutRepository = agentPayoutRepository;
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    @Override
    public AgentPayoutDTO create(AgentPayoutDTO dto) {

        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        AgentPayout entity = AgentPayoutMapper.toEntity(dto);
        entity.setDeliveryAgent(agent);

        if (entity.getStatus() == PayoutStatus.PAID) {
            entity.setPaidAt(
                    dto.getPaidAt() != null
                            ? dto.getPaidAt()
                            : LocalDateTime.now()
            );
        } else {
            entity.setPaidAt(null);
        }

        AgentPayout saved = agentPayoutRepository.save(entity);
        return AgentPayoutMapper.toDTO(saved);
    }

    @Override
    public List<AgentPayoutDTO> getAll() {
        return agentPayoutRepository.findAll()
                .stream()
                .map(AgentPayoutMapper::toDTO)
                .toList();
    }

    @Override
    public AgentPayoutDTO getById(Long id) {

        AgentPayout entity = agentPayoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent Payout not found"));

        return AgentPayoutMapper.toDTO(entity);
    }

    @Override
    public List<AgentPayoutDTO> getByDeliveryAgentId(Long agentId) {

        return agentPayoutRepository.findByDeliveryAgentId(agentId)
                .stream()
                .map(AgentPayoutMapper::toDTO)
                .toList();
    }

    @Override
    public AgentPayoutDTO update(Long id, AgentPayoutDTO dto) {

        AgentPayout existing = agentPayoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent Payout not found"));

        DeliveryAgent agent = deliveryAgentRepository.findById(dto.getDeliveryAgentId())
                .orElseThrow(() -> new ResourceNotFoundException("Delivery Agent not found"));

        existing.setDeliveryAgent(agent);
        existing.setAmount(dto.getAmount());
        existing.setStatus(dto.getStatus());
        existing.setTransactionReference(dto.getTransactionReference());

        if (dto.getStatus() == PayoutStatus.PAID) {
            existing.setPaidAt(
                    dto.getPaidAt() != null
                            ? dto.getPaidAt()
                            : LocalDateTime.now()
            );
        } else {
            existing.setPaidAt(null);
        }

        AgentPayout updated = agentPayoutRepository.save(existing);

        return AgentPayoutMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {

        AgentPayout existing = agentPayoutRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agent Payout not found"));

        agentPayoutRepository.delete(existing);
    }
}