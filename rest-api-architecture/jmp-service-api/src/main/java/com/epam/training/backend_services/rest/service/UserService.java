package com.epam.training.backend_services.rest.service;

import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto create(UserRequestDto userRequestDto);
    UserResponseDto get(Long userId);
    List<UserResponseDto> getAll();
    UserResponseDto update(UserRequestDto userRequestDto);
    void delete(Long userId);

}
