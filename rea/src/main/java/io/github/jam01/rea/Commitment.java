package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Commitment {
    private final @Nullable CommitmentType type;
    private final @Nullable EventType eventType;
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

    protected Commitment(@Nullable CommitmentType commitmentType,
                         Agent provider, Agent receiver,
                         List<? extends Reservation> reservations,
                         @Nullable EventType eventType,
                         List<Event<? extends Stockflow>> executedBy,
                         boolean isFulfilled) {
        Objects.requireNonNull(provider);
        Objects.requireNonNull(receiver);

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

    public Commitment extendExecutedBy(List<Event<? extends Stockflow>> events) {
        List<Event<? extends Stockflow>> list = new ArrayList<>(executedBy.size() + events.size());
        list.addAll(executedBy);
        list.addAll(events);
        return this.executedBy(list);
    }

    protected abstract Commitment fulfilled(boolean isFulfilled);

    protected abstract Commitment executedBy(List<Event<? extends Stockflow>> events);
}
