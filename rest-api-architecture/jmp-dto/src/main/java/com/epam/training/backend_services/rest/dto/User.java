package com.epam.training.backend_services.rest.dto;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
