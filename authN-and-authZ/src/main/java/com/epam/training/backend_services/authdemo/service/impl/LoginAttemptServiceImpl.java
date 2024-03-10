package com.epam.training.backend_services.authdemo.service.impl;

import com.epam.training.backend_services.authdemo.service.LoginAttemptService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

@Slf4j
@Service
@EnableScheduling
public class LoginAttemptServiceImpl implements LoginAttemptService {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final int ALLOWED_FAILED_ATTEMPTS = 2;
    private static final Duration SHOULD_WAIT = Duration.ofSeconds(30);

    private final Map<String, Attempt> failedAttempts = new HashMap<>();
    private final Map<String, LocalDateTime> blockedUsers = new HashMap<>();
    private final Predicate<LocalDateTime> outdated = (dt) -> {
        log.info("Check: {} {}", format.format(dt), format.format(LocalDateTime.now()));
        log.info("isBefore: {}", dt.isBefore(LocalDateTime.now()));
        return dt.isBefore(LocalDateTime.now());
    };

    @Override
    public boolean canAttemptNow(String userKey) {
        return Optional.ofNullable(blockedUsers.get(userKey))
                .map(outdated::test)
                .orElse(true);
    }

    @Override
    public long shouldWait(String userKey) {
        return Optional.ofNullable(blockedUsers.get(userKey))
                .map(dt -> Duration.between(LocalDateTime.now(), dt.plus(SHOULD_WAIT)))
                .orElse(Duration.ofSeconds(0)).toSeconds();
    }

    @Override
    public void registerFailedAttempt(String userKey) {
        failedAttempts.compute(userKey, (key, attempt) -> {
            log.info("key: {}", key);
            if (attempt != null) {
                if (attempt.failedCounts.get() < ALLOWED_FAILED_ATTEMPTS - 1) {
                    attempt.failedCounts.getAndIncrement();
                    attempt.lastFailedDateTime = LocalDateTime.now();
                } else {
                    blockedUsers.put(key, LocalDateTime.now().plus(SHOULD_WAIT));
                    attempt.lastFailedDateTime = LocalDateTime.now();
                    return attempt;
                }
            } else {
                return new Attempt(new AtomicInteger(1), LocalDateTime.now());
            }
            return attempt;
        });
    }

    @Override
    public void clearHistory(String userKey) {
        failedAttempts.remove(userKey);
        blockedUsers.remove(userKey);
    }

    @Scheduled(fixedDelay = 1000)
    public void clean() {
        log.info("blockedUsers: {}", blockedUsers);
        log.info("failedAttempts: {}", failedAttempts);
        blockedUsers.entrySet().removeIf(e -> outdated.test(e.getValue()));
        failedAttempts.entrySet().removeIf(e -> outdated.test(e.getValue().lastFailedDateTime.plus(SHOULD_WAIT)));
    }

    @Data
    @AllArgsConstructor
    private static class Attempt {
        private AtomicInteger failedCounts;
        private LocalDateTime lastFailedDateTime;
    }

    public Map<String, LocalDateTime> getBlockedUsers() {
        return blockedUsers;
    }
}
