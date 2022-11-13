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

    protected Event(T stockflow, Agent provider, Agent receiver) {
        this(null, stockflow, provider, receiver);
    }

    protected Event(EventType specification, T stockflow, Agent provider, Agent receiver) {
        this.specification = specification;
        this.stockflow = stockflow;
        this.provider = provider;
        this.receiver = receiver;
    }

    public static abstract class Increment extends Event<Stockflow.Increment> {
        public Increment(Stockflow.Increment stockflow, Agent provider, Agent receiver) {
            super(stockflow, provider, receiver);
        }

        public Increment(EventType specification, Stockflow.Increment stockflow, Agent provider, Agent receiver) {
            super(specification, stockflow, provider, receiver);
        }
    }

    public static abstract class Decrement extends Event<Stockflow.Decrement> {
        public Decrement(Stockflow.Decrement stockflow, Agent provider, Agent receiver) {
            super(stockflow, provider, receiver);
        }

        public Decrement(EventType specification, Stockflow.Decrement stockflow, Agent provider, Agent receiver) {
            super(specification, stockflow, provider, receiver);
        }
    }
}
