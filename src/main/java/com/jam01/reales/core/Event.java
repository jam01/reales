package com.jam01.reales.core;

import java.util.List;
import java.util.Optional;

public abstract class Event<T extends Stockflow> {
    private final EventType specification;
    public final List<T> stockflow;
    public final Agent provider;
    public final Agent receiver;

    public Optional<EventType> specification() {
        return Optional.ofNullable(specification);
    }

    protected Event(EventType specification, List<T> stockflow, Agent provider, Agent receiver) {
        this.specification = specification;
        this.stockflow = stockflow;
        this.provider = provider;
        this.receiver = receiver;
    }

    protected Event(List<T> stockflow, Agent provider, Agent receiver) {
        this(null, stockflow, provider, receiver);
    }
}
