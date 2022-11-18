package com.jam01.reales.core;

public abstract class CommitmentType extends Commitment {
    protected CommitmentType(Reservation reservation, Agent provider, Agent receiver) {
        super(reservation, provider, receiver);
    }

    protected CommitmentType(Reservation reservation, Agent provider, Agent receiver, EventType eventType) {
        super(reservation, provider, receiver, eventType);
    }
}
