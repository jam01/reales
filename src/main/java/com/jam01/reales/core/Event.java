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

    public static abstract class Increment extends Event<Stockflow.Increment> {
        public Increment(List<Stockflow.Increment> stockflow, Agent provider, Agent receiver) {
            super(stockflow, provider, receiver);
        }

        public Increment(EventType specification, List<Stockflow.Increment> stockflow, Agent provider, Agent receiver) {
            super(specification, stockflow, provider, receiver);
        }
    }

    public static abstract class Decrement extends Event<Stockflow.Decrement> {
        public Decrement(List<Stockflow.Decrement> stockflow, Agent provider, Agent receiver) {
            super(stockflow, provider, receiver);
        }

        public Decrement(EventType specification, List<Stockflow.Decrement> stockflow, Agent provider, Agent receiver) {
            super(specification, stockflow, provider, receiver);
        }
    }
}
