package com.epam.training.backend_services.authdemo.service;

import com.epam.training.backend_services.authdemo.domain.model.State;
import com.epam.training.backend_services.authdemo.domain.repository.StateRepository;
import com.epam.training.backend_services.authdemo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;
    private final Random random = new Random();

    public String get(Long stateId) {
        return stateRepository.findById(stateId).map(State::getContent)
                .orElseThrow(() -> new NotFoundException("No state with id: " + stateId));
    }

    public List<String> getAll() {
        return stateRepository.findAll().stream()
                .map(State::getContent)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getRandom() {
        List<String> statesList = getAll();
        return statesList.get(random.nextInt(statesList.size()));
    }

}
