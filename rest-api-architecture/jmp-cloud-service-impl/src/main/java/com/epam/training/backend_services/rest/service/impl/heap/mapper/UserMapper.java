package com.epam.training.backend_services.rest.service.impl.heap.mapper;

import com.epam.training.backend_services.rest.dto.User;
import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userDtoToEntity(UserRequestDto dto);

    UserResponseDto userEntityToResponse(User entity);

}
