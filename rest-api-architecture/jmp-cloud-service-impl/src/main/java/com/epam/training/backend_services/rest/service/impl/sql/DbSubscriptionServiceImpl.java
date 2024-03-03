package com.epam.training.backend_services.rest.service.impl.sql;

import com.epam.training.backend_services.rest.dto.SubscriptionRequestDto;
import com.epam.training.backend_services.rest.dto.SubscriptionResponseDto;
import com.epam.training.backend_services.rest.exception.NotFoundException;
import com.epam.training.backend_services.rest.service.SubscriptionService;
import com.epam.training.backend_services.rest.service.impl.sql.domain.model.Subscription;
import com.epam.training.backend_services.rest.service.impl.sql.domain.model.User;
import com.epam.training.backend_services.rest.service.impl.sql.domain.repository.SubscriptionRepository;
import com.epam.training.backend_services.rest.service.impl.sql.domain.repository.UserRepository;
import com.epam.training.backend_services.rest.service.impl.sql.mapper.SqlSubscriptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service("DbSubscriptionService")
@RequiredArgsConstructor
public class DbSubscriptionServiceImpl implements SubscriptionService {

    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final SqlSubscriptionMapper subscriptionMapper;

    @Override
    public SubscriptionResponseDto create(SubscriptionRequestDto subscriptionRequestDto) {
        User user = userRepository.findById(subscriptionRequestDto.getUserId())
                .orElseThrow(() -> new NotFoundException("No user with id: " + subscriptionRequestDto.getUserId()));
        Subscription subscription = subscriptionMapper.userDtoToEntity(subscriptionRequestDto);
        subscription.setUser(user);
        return subscriptionMapper.subscriptionEntityToResponse(subscriptionRepository.save(subscription));
    }

    @Override
    public SubscriptionResponseDto get(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId).map(subscriptionMapper::subscriptionEntityToResponse)
                .orElseThrow(() -> new NotFoundException(String .format("Subscription %d not found", subscriptionId)));
    }

    @Override
    public List<SubscriptionResponseDto> getAll() {
        return subscriptionRepository.findAll().stream()
                .map(subscriptionMapper::subscriptionEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public SubscriptionResponseDto update(SubscriptionRequestDto subscriptionRequestDto) {
        return subscriptionRepository.findById(subscriptionRequestDto.getId())
                .map(subscription -> {
                    subscription.setUser(userRepository.findById(subscriptionRequestDto.getUserId())
                            .orElseThrow(() -> new NotFoundException(
                                    String .format("Subscription %d not found", subscriptionRequestDto.getId()))));
                    subscription.setStartDate(subscriptionRequestDto.getStartDate());
                    subscriptionRepository.save(subscription);
                    return subscription;
                }).map(subscriptionMapper::subscriptionEntityToResponse)
                .orElseThrow(() -> new NotFoundException(
                        String .format("Subscription %d not found", subscriptionRequestDto.getId())));
    }

    @Override
    public void delete(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }
}
