package com.example.backend.dto;

import com.example.backend.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp="^[0-9]{10}$")
    private String phone;

    @NotBlank
    @Size(min=8, max=20)
    private String password;

    @NotNull
    private Role role;

    private Boolean isActive;
}