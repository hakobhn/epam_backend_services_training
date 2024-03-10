package com.epam.training.backend_services.authdemo.service;

import com.epam.training.backend_services.authdemo.domain.model.User;
import com.epam.training.backend_services.authdemo.domain.repository.UserRepository;
import com.epam.training.backend_services.authdemo.exception.NotFoundException;
import com.epam.training.backend_services.authdemo.mapper.UserMapper;
import com.epam.training.backend_services.authdemo.web.dto.UserDto;
import com.epam.training.backend_services.authdemo.web.dto.UserInput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto create(@NotNull UserInput input) {
        return userMapper.userEntityToDto(
                userRepository.save(userMapper.userInputToEntity(input)));
    }

    public UserDto get(Long userId) {
        return userRepository.findById(userId).map(userMapper::userEntityToDto)
                .orElseThrow(() -> new NotFoundException("No user with id: " + userId));
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userEntityToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getAllAdmins() {
        List<UserDto> admins = new ArrayList<>();
        Iterable<User> iterable = userRepository.findAllAdmins();
        iterable.forEach(
                usr -> {
                    UserDto userDto = userMapper.userEntityToDto(usr);
                    admins.add(userDto);
                });
        return admins;
    }

}
