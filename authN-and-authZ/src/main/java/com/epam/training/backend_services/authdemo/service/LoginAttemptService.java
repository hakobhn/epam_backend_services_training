package com.epam.training.backend_services.authdemo.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface LoginAttemptService {
    boolean canAttemptNow(String user);
    long shouldWait(String user);
    void registerFailedAttempt(String user);
    void clearHistory(String name);
    Map<String, LocalDateTime> getBlockedUsers();
}
