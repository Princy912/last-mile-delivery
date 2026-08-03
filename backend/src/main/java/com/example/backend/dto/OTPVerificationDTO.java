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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "10")
    private Long deliveryOrderId;

    @NotBlank
    @Size(min = 4, max = 6)
    @Schema(example = "123456")
    private String otpCode;

    @Schema(example = "false")
    private Boolean isVerified;

    @NotNull
    @Schema(example = "2026-08-03T21:20:00")
    private LocalDateTime expiredAt;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "2026-08-03T21:10:00")
    private LocalDateTime verifiedAt;
}
