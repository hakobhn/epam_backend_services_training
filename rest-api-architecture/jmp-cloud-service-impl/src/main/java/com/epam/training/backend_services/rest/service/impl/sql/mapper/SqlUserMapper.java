package com.epam.training.backend_services.rest.service.impl.sql.mapper;

import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;
import com.epam.training.backend_services.rest.service.impl.sql.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SqlUserMapper {

    User userDtoToEntity(UserRequestDto dto);

    UserResponseDto userEntityToResponse(User user);

}
