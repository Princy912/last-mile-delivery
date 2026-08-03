package com.example.backend.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "Bearer")
    private String type;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "john.doe@example.com")
    private String email;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "CUSTOMER")
    private String role;
}
