package com.example.backend.dto;

import com.example.backend.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Role role;

    private Boolean isActive;
}