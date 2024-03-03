package com.epam.training.backend_services.rest.service.impl.heap;

import com.epam.training.backend_services.rest.dto.Subscription;
import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;
import com.epam.training.backend_services.rest.dto.User;
import com.epam.training.backend_services.rest.exception.NotFoundException;
import com.epam.training.backend_services.rest.service.SubscriptionService;
import com.epam.training.backend_services.rest.service.impl.heap.mapper.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("HeapSubscriptionService")
@RequiredArgsConstructor
public class HeapSubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionMapper subscriptionMapper;

    private final StorageManager<User> userStorageManager;
    private final StorageManager<Subscription> subscriptionStorageManager;

    @Override
    public SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequestDto) {
        User user = userStorageManager.getStorage().get(subscriptionRequestDto.getUserId());
        Long subscriptionId = subscriptionStorageManager.getIdSequence().incrementAndGet();
        Subscription subscription = new Subscription();
        subscription.setId(subscriptionId);
        subscription.setUser(user);
        subscription.setStartDate(LocalDate.now());
        subscriptionStorageManager.getStorage().put(subscriptionId, subscription);

        return subscriptionMapper.subscriptionEntityToResponse(subscription);
    }

    @Override
    public SubscriptionResponseDto get(Long subscriptionId) {
        return Optional.ofNullable(subscriptionStorageManager.getStorage().get(subscriptionId))
                .map(subscriptionMapper::subscriptionEntityToResponse)
                .orElseThrow(() -> new NotFoundException(String .format("Subscription %d not found", subscriptionId)));
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        return subscriptionStorageManager.getStorage().values().stream()
                .map(subscriptionMapper::subscriptionEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public SubscriptionResponseDto update(SubscriptionRequestDto subscriptionRequestDto) {
        return Optional.ofNullable(subscriptionStorageManager.getStorage().get(subscriptionRequestDto.getId()))
                .map(subscription -> {
                    subscription.setUser(
                            Optional.ofNullable(userStorageManager.getStorage().get(subscriptionRequestDto.getUserId()))
                                    .orElseThrow(() -> new NotFoundException(
                                            String .format("User %d not found", subscriptionRequestDto.getUserId()))));
                    return subscription;
                }).map(subscriptionMapper::subscriptionEntityToResponse)
                .orElseThrow(() -> new NotFoundException(
                        String .format("Subscription %d not found", subscriptionRequestDto.getId())));
    }

    @Override
    public void delete(Long subscriptionId) {
        Optional.ofNullable(subscriptionStorageManager.getStorage().get(subscriptionId))
                .map(sb -> {
                    subscriptionStorageManager.getStorage().remove(sb.getId());
                    return sb;
                }).orElseThrow(() -> new NotFoundException("No subscription with id: " + subscriptionId));
    }
}
