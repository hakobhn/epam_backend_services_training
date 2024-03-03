package com.epam.training.backend_services.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UserResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
