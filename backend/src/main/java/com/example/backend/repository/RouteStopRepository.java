package com.example.backend.repository;

import com.example.backend.entity.RouteStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteStopRepository extends JpaRepository<RouteStop, Long> {
    List<RouteStop> findByRouteId(Long routeId);
    Optional<RouteStop> findByDeliveryOrderId(Long orderId);
}
