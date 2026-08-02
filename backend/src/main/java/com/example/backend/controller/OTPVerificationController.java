package com.example.backend.controller;

import com.example.backend.dto.OTPVerificationDTO;
import com.example.backend.service.OTPVerificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/otp-verifications")
public class OTPVerificationController {

    private final OTPVerificationService otpVerificationService;

    public OTPVerificationController(OTPVerificationService otpVerificationService) {
        this.otpVerificationService = otpVerificationService;
    }

    @PostMapping
    public ResponseEntity<OTPVerificationDTO> create(@Valid @RequestBody OTPVerificationDTO dto) {
        return new ResponseEntity<>(otpVerificationService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OTPVerificationDTO>> getAll() {
        return ResponseEntity.ok(otpVerificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OTPVerificationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(otpVerificationService.getById(id));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OTPVerificationDTO> getByDeliveryOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(otpVerificationService.getByDeliveryOrderId(orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OTPVerificationDTO> update(@PathVariable Long id, @Valid @RequestBody OTPVerificationDTO dto) {
        return ResponseEntity.ok(otpVerificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        otpVerificationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
