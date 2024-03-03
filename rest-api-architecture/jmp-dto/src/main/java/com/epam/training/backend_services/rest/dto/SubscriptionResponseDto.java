package com.epam.training.backend_services.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionResponseDto {
    private Long id;
    private Long userId;
    private LocalDate startDate;
}
