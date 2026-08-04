package com.example.backend.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String token;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String type;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String email;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "string")
    private String role;
}
