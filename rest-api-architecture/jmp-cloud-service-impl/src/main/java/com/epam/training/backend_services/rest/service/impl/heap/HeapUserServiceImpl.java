package com.epam.training.backend_services.rest.service.impl.heap;

import com.epam.training.backend_services.rest.dto.User;
import com.epam.training.backend_services.rest.dto.UserRequestDto;
import com.epam.training.backend_services.rest.dto.UserResponseDto;
import com.epam.training.backend_services.rest.exception.NotFoundException;
import com.epam.training.backend_services.rest.service.UserService;
import com.epam.training.backend_services.rest.service.impl.heap.mapper.UserMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("HeapUserService")
@RequiredArgsConstructor
public class HeapUserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final StorageManager<User> userStorageManager;

    @Override
    public UserResponseDto create(@NotNull UserRequestDto userRequestDto) {
        User user = userMapper.userDtoToEntity(userRequestDto);
        user.setId(userStorageManager.getIdSequence().incrementAndGet());
        userStorageManager.getStorage().put(user.getId(), user);

        return userMapper.userEntityToResponse(user);
    }

    @Override
    public UserResponseDto get(Long userId) {
        return userMapper.userEntityToResponse(userStorageManager.getStorage().get(userId));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userStorageManager.getStorage().values().stream()
                .map(userMapper::userEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) {
        return Optional.ofNullable(userRequestDto.getId())
                .map(userStorageManager.getStorage()::get)
                .map(ent -> {
                    BeanUtils.copyProperties(userRequestDto, ent, "id");
                    return ent;
                }).map(userMapper::userEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No user with id: " + userRequestDto.getId()));
    }

    @Override
    public void delete(Long userId) {
        Optional.ofNullable(userStorageManager.getStorage().get(userId))
                .map(usr -> {
                    userStorageManager.getStorage().remove(usr.getId());
                    return usr;
                }).orElseThrow(() -> new NotFoundException("No user with id: " + userId));
    }

}
