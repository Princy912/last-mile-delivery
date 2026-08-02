package com.example.backend.controller;

import com.example.backend.dto.DeliveryAddressDTO;
import com.example.backend.service.DeliveryAddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-addresses")
public class DeliveryAddressController {

    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostMapping
    public ResponseEntity<DeliveryAddressDTO> create(@Valid @RequestBody DeliveryAddressDTO dto) {
        return new ResponseEntity<>(deliveryAddressService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAddressDTO>> getAll() {
        return ResponseEntity.ok(deliveryAddressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAddressDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryAddressService.getById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeliveryAddressDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(deliveryAddressService.getByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAddressDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryAddressDTO dto) {
        return ResponseEntity.ok(deliveryAddressService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
