package com.epam.training.backend_services.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscriptionRequestDto {
    private Long id;
    @NotNull
    private Long userId;
    private LocalDate startDate;
}
