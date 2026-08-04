package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import com.example.backend.enums.AgentStatus;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAgentDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long userId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String userName;

    @NotBlank
    @Schema(example = "string")
    private String vehicleType;

    @NotNull
    @Schema(example = "0")
    private BigDecimal currentLat;

    @NotNull
    @Schema(example = "0")
    private BigDecimal currentLng;

    @NotNull
    @Schema(example = "AVAILABLE")
    private AgentStatus status;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private BigDecimal rating;
}