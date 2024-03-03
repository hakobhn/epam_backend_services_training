package com.epam.training.backend_services.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Subscription {
    private Long id;
    private User user;
    private LocalDate startDate;
}
