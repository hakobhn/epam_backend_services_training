package com.epam.training.backend_services.rest.service.impl.sql;

import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;
import com.epam.training.backend_services.rest.exception.NotFoundException;
import com.epam.training.backend_services.rest.service.UserService;
import com.epam.training.backend_services.rest.service.impl.sql.domain.repository.UserRepository;
import com.epam.training.backend_services.rest.service.impl.sql.mapper.SqlUserMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service("DbUserService")
@RequiredArgsConstructor
public class DbUserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SqlUserMapper userMapper;

    @Override
    public UserResponseDto create(@NotNull UserRequestDto userRequestDto) {
        return userMapper.userEntityToResponse(
                userRepository.save(userMapper.userDtoToEntity(userRequestDto)));
    }

    @Override
    public UserResponseDto get(Long userId) {
        return userRepository.findById(userId).map(userMapper::userEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No user with id: " + userId));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) {
        return userRepository.findById(userRequestDto.getId())
                .map(ent -> {
                    BeanUtils.copyProperties(userRequestDto, ent, "id");
                    return ent;
                })
                .map(userMapper::userEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No user with id: " + userRequestDto.getId()));
    }

    @Override
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
