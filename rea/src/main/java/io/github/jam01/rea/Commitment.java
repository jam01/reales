package io.github.jam01.rea;

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
        this.provider = provider;
        this.receiver = receiver;
        this.reservations = reservations != null ? Collections.unmodifiableList(reservations) : Collections.emptyList();
        this.eventType = eventType;
        this.executedBy = executedBy != null ? Collections.unmodifiableList(executedBy) : Collections.emptyList();
        this.isFulfilled = isFulfilled;
    }

    public Optional<CommitmentType> type() {
        return Optional.ofNullable(type);
    }

    public Optional<EventType> eventType() {
        return Optional.ofNullable(eventType);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Commitment> T executedBy(T origin, List<Event<? extends Stockflow>> events) {
        return (T) origin.executedBy(events);
    }

    protected abstract Commitment executedBy(List<Event<? extends Stockflow>> events);

    public Commitment extendExecutedBy(List<Event<? extends Stockflow>> events) {
        List<Event<? extends Stockflow>> list = new ArrayList<>(executedBy.size() + events.size());
        list.addAll(executedBy);
        list.addAll(events);
        return this.executedBy(list);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Commitment> T fulfilled(T origin, boolean isFulfilled) {
        return (T) origin.fulfilled(isFulfilled);
    }

    protected abstract Commitment fulfilled(boolean isFulfilled);
}
