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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull
    @Schema(example = "2")
    private Long userId;

    @NotBlank
    @Schema(example = "123 Main St")
    private String addressLine1;

    @Schema(example = "Apt 4B")
    private String addressLine2;

    @NotBlank
    @Schema(example = "San Francisco")
    private String city;

    @NotBlank
    @Schema(example = "CA")
    private String state;

    @NotBlank
    @Schema(example = "94107")
    private String postalCode;

    @NotBlank
    @Schema(example = "USA")
    private String country;

    @Schema(example = "true")
    private Boolean isDefault;
}
