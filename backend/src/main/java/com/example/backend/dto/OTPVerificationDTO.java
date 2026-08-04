package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long deliveryOrderId;

    @NotBlank
    @Size(min = 4, max = 6)
    @Schema(example = "string")
    private String otpCode;

    @Schema(example = "true")
    private Boolean isVerified;

    @NotNull
    @Schema(example = "2026-01-01T10:00:00")
    private LocalDateTime expiredAt;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-01-01T10:00:00")
    private LocalDateTime verifiedAt;
}
