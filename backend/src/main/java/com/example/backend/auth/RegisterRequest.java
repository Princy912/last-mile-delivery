package com.example.backend.auth;

import com.example.backend.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[A-Za-z ]{2,100}$", message = "Name must contain only alphabets and spaces")
    @Schema(example = "string")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(example = "string")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain exactly 10 digits")
    @Schema(example = "string")
    private String phone;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,50}$", message = "Password must contain uppercase, lowercase, number and special character.")
    @Schema(example = "string")
    private String password;

    @NotNull
    @Schema(example = "CUSTOMER")
    private Role role;
}
