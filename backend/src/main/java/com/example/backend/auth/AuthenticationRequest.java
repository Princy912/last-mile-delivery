package com.example.backend.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank
    @Email
    @Schema(example = "john.doe@example.com")
    private String email;

    @NotBlank
    @Schema(example = "SecurePass123!")
    private String password;
}
