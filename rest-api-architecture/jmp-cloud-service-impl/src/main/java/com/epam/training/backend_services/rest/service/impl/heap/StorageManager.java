package com.epam.training.backend_services.rest.service.impl.heap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class StorageManager<T> {

    private final AtomicLong idSequence = new AtomicLong(0);
    private final Map<Long, T> storage = new LinkedHashMap<>();

    public Map<Long, T> getStorage() {
        return storage;
    }

    public AtomicLong getIdSequence() {
        return idSequence;
    }
}
