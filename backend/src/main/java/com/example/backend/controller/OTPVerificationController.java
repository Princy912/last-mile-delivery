package com.example.backend.controller;

import com.example.backend.dto.OTPVerificationDTO;
import com.example.backend.service.OTPVerificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/otp-verifications")
public class OTPVerificationController {

    private final OTPVerificationService otpVerificationService;

    public OTPVerificationController(OTPVerificationService otpVerificationService) {
        this.otpVerificationService = otpVerificationService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OTPVerificationDTO> create(@Valid @RequestBody OTPVerificationDTO dto) {
        return new ResponseEntity<>(otpVerificationService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER')")
    public ResponseEntity<List<OTPVerificationDTO>> getAll() {
        return ResponseEntity.ok(otpVerificationService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'CUSTOMER')")
    public ResponseEntity<OTPVerificationDTO> getById(@PathVariable Long id) {
        // TODO: Implement ownership check to ensure CUSTOMER can only view their own OTP verification records
        return ResponseEntity.ok(otpVerificationService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERATIONS_MANAGER', 'CUSTOMER')")
    public ResponseEntity<OTPVerificationDTO> getByDeliveryOrderId(@PathVariable Long orderId) {
        // TODO: Implement ownership check to ensure CUSTOMER can only view their own OTP verification records
        return ResponseEntity.ok(otpVerificationService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('DELIVERY_AGENT', 'ADMIN')")
    public ResponseEntity<OTPVerificationDTO> update(@PathVariable Long id, @Valid @RequestBody OTPVerificationDTO dto) {
        // TODO: Implement verification/ownership checks for DELIVERY_AGENT to ensure they verify OTP for their assigned orders
        return ResponseEntity.ok(otpVerificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        otpVerificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
