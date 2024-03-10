package com.epam.training.backend_services.authdemo.mapper;

import com.epam.training.backend_services.authdemo.component.PasswordEncoderMapper;
import com.epam.training.backend_services.authdemo.domain.model.User;
import com.epam.training.backend_services.authdemo.web.dto.UserDto;
import com.epam.training.backend_services.authdemo.web.dto.UserInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {

    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    User userInputToEntity(UserInput input);

    User userDtoToEntity(UserDto dto);

    UserDto userEntityToDto(User user);

}
