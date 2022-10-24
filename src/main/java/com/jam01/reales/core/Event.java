package com.jam01.reales.core;

import java.util.Optional;

public abstract class Event<T extends Stockflow> {
    private final EventType specification;
    private final T stockflow;
    private final Agent provider;
    private final Agent receiver;

    public Optional<EventType> specification() {
        return Optional.ofNullable(specification);
    }

    public T stockflow() {
        return stockflow;
    }

    public Agent provider() {
        return provider;
    }

    public Agent receiver() {
        return receiver;
    }

    public Event(T stockflow, Agent provider, Agent receiver) {
        this(null, stockflow, provider, receiver);
    }

    public Event(EventType specification, T stockflow, Agent provider, Agent receiver) {
        this.specification = specification;
        this.stockflow = stockflow;
        this.provider = provider;
        this.receiver = receiver;
    }
}
