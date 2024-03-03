package com.epam.training.backend_services.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDto {
    private Long id;
    @Size(min = 3, max = 10)
    private String name;
    @Size(min = 3, max = 10)
    private String surname;
    @NotNull
    private LocalDate birthday;
}
