package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTPVerificationDTO {

    private Long id;

    @NotNull
    private Long deliveryOrderId;

    @NotBlank
    @Size(min = 4, max = 6)
    private String otpCode;

    private Boolean isVerified;

    @NotNull
    private LocalDateTime expiredAt;

    private LocalDateTime verifiedAt;
}
