package com.example.backend.repository;

import com.example.backend.entity.AgentPayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentPayoutRepository extends JpaRepository<AgentPayout, Long> {
    List<AgentPayout> findByDeliveryAgentId(Long agentId);
}
