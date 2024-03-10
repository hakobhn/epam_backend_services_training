package com.epam.training.backend_services.authdemo.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockedDto {
    private String key;
    private long shouldWait;
}
