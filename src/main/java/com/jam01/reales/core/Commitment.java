package com.jam01.reales.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Commitment {
    public final CommitmentType type;
    public final EventType eventType;
    public final List<? extends Reservation> reservations;
    public final Agent provider;
    public final Agent receiver;
    public final boolean isFulfilled;

    public final List<Event<? extends Stockflow>> executedBy;

    protected Commitment(Agent provider, Agent receiver,
                         List<? extends Reservation> reservations) {
        this(null, provider, receiver, reservations, null, Collections.emptyList(), false);
    }

    protected Commitment(Agent provider, Agent receiver,
                         List<? extends Reservation> reservations,
                         boolean isFulfilled) {
        this(null, provider, receiver, reservations, null, Collections.emptyList(), isFulfilled);
    }

    protected Commitment(CommitmentType commitmentType,
                         Agent provider, Agent receiver,
                         List<? extends Reservation> reservations,
                         EventType eventType,
                         List<Event<? extends Stockflow>> executedBy,
                         boolean isFulfilled) {
        this.type = commitmentType;
        this.eventType = eventType;
        this.reservations = reservations != null ? Collections.unmodifiableList(reservations) : Collections.emptyList();
        this.provider = provider;
        this.receiver = receiver;
        this.executedBy = executedBy != null ? Collections.unmodifiableList(executedBy) : Collections.emptyList();
        this.isFulfilled = isFulfilled;
    }

    public Optional<CommitmentType> type() {
        return Optional.ofNullable(type);
    }

    public Optional<EventType> eventType() {
        return Optional.ofNullable(eventType);
    }

    public abstract Commitment executedBy(List<Event<? extends Stockflow>> events);

    public Commitment extendExecutedBy(List<Event<? extends Stockflow>> events) {
        List<Event<? extends Stockflow>> list = new ArrayList<>(executedBy.size() + events.size());
        list.addAll(executedBy);
        list.addAll(events);
        return this.executedBy(list);
    }

//    public abstract Commitment fulfilled(boolean isFulfilled);

//    public Commitment fulfill(List<Event<? extends Stockflow>> events) {
//        if (this.eventType != null)
//            for (Event<? extends Stockflow> event : events) {
//                if (event.type().isEmpty())
//                    throw new IllegalArgumentException("");
//                else if (!event.type().get().equals(this.eventType)) {
//                    throw new IllegalArgumentException("Cannot ");
//                }
//            }
//        return this.fulfilled(events);
//    }
}
