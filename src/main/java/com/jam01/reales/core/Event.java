package com.jam01.reales.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Event<T extends Stockflow> {
    private final EventType type;
    public final List<T> stockflow;
    public final Agent provider;
    public final Agent receiver;

    public Optional<EventType> type() {
        return Optional.ofNullable(type);
    }

    protected Event(EventType type, Agent provider, Agent receiver, List<T> stockflow) {
        this.type = type;
        this.stockflow = stockflow != null ? Collections.unmodifiableList(stockflow) : Collections.emptyList();
        this.provider = provider;
        this.receiver = receiver;
    }

    protected Event(Agent provider, Agent receiver, List<T> stockflow) {
        this(null, provider, receiver, stockflow);
    }
}
