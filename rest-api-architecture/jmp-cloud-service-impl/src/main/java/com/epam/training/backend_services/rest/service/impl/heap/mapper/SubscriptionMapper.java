package com.epam.training.backend_services.rest.service.impl.heap.mapper;

import com.epam.training.backend_services.rest.dto.Subscription;
import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription subscriptionDtoToEntity(SubscriptionRequestDto dto);

    @Mapping(target="userId", expression="java(entity.getUser().getId())")
    SubscriptionResponseDto subscriptionEntityToResponse(Subscription entity);

}
