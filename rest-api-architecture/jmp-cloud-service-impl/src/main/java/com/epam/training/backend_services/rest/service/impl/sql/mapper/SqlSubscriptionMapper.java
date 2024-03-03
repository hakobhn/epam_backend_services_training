package com.epam.training.backend_services.rest.service.impl.sql.mapper;

import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;
import com.epam.training.backend_services.rest.service.impl.sql.domain.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SqlSubscriptionMapper {

    Subscription userDtoToEntity(SubscriptionRequestDto dto);

    @Mapping(target="userId", expression="java(entity.getUser().getId())")
    SubscriptionResponseDto subscriptionEntityToResponse(Subscription entity);

}
