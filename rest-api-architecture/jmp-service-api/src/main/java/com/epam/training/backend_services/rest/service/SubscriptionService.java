package com.epam.training.backend_services.rest.service;

import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequestDto);
    SubscriptionResponseDto get(Long subscriptionId);
    List<SubscriptionResponseDto> getAll();
    SubscriptionResponseDto update(SubscriptionRequestDto subscriptionRequestDto);
    void delete(Long subscriptionId);

}
