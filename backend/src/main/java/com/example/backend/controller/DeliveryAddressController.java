package com.example.backend.controller;

import com.example.backend.dto.DeliveryAddressDTO;
import com.example.backend.service.DeliveryAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/delivery-addresses")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<DeliveryAddressDTO> create(@Valid @RequestBody DeliveryAddressDTO dto) {
        return new ResponseEntity<>(deliveryAddressService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DeliveryAddressDTO>> getAll() {
        return ResponseEntity.ok(deliveryAddressService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<DeliveryAddressDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure customers can only read their own addresses
        return ResponseEntity.ok(deliveryAddressService.getById(id));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<List<DeliveryAddressDTO>> getByUserId(@PathVariable Long userId) {
        // TODO: Implement ownership validation to ensure customers can only read their own addresses
        return ResponseEntity.ok(deliveryAddressService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<DeliveryAddressDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryAddressDTO dto) {
        // TODO: Implement ownership validation to ensure customers can only update their own addresses
        return ResponseEntity.ok(deliveryAddressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO: Implement ownership validation to ensure customers can only delete their own addresses
        deliveryAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
