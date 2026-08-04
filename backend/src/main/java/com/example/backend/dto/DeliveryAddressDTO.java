package com.example.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryAddressDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "0")
    private Long id;

    @NotNull
    @Schema(example = "0")
    private Long userId;

    @NotBlank
    @Schema(example = "string")
    private String addressLine1;

    @Schema(example = "string")
    private String addressLine2;

    @NotBlank
    @Schema(example = "string")
    private String city;

    @NotBlank
    @Schema(example = "string")
    private String state;

    @NotBlank
    @Schema(example = "string")
    private String postalCode;

    @NotBlank
    @Schema(example = "string")
    private String country;

    @Schema(example = "true")
    private Boolean isDefault;
}
