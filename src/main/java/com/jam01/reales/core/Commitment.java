package com.jam01.reales.core;

import java.util.Optional;

public abstract class Commitment {
    public final EventType eventType;
    public final Reservation reservation;
    public final Agent provider;
    public final Agent receiver;
    private final boolean isFulfilled = false;

    protected Commitment(Reservation reservation, Agent provider, Agent receiver) {
        this(reservation, provider, receiver, null);
    }

    protected Commitment(Reservation reservation, Agent provider, Agent receiver, EventType eventType) {
        this.eventType = eventType;
        this.reservation = reservation;
        this.provider = provider;
        this.receiver = receiver;
    }

    public Optional<EventType> eventSpecification() {
        return Optional.ofNullable(eventType);
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }
}
