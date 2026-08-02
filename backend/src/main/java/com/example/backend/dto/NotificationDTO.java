package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    private Long id;

    @NotNull
    private Long userId;

    private Long deliveryOrderId;

    @NotBlank
    private String title;

    @NotBlank
    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;
}
