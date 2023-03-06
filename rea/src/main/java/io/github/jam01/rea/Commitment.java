package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * An agreement to execute an Economic Event in a well-defined future that will result in either an increase of
 * resources or a decrease of resources.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Commitment {
    public final List<? extends Reservation> reservations;
    public final Agent provider;
    public final Agent receiver;
    public final boolean isFulfilled;
    public final List<Event> executedBy;
    private final @Nullable CommitmentType type;
    private final @Nullable EventType eventType;

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
                         List<Event> executedBy,
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

    /**
     * The type of Commitment.
     *
     * @return Optional[CommitmentType] of this Commitment
     */
    public Optional<CommitmentType> type() {
        return Optional.ofNullable(type);
    }

    // TODO: 2/28/23 Should be a Set<Commitment>?
    /**
     * The type of Event expected to fulfill this Commitment.
     *
     * @return Optional[CommitmentType] of this Commitment
     */
    public Optional<EventType> eventType() {
        return Optional.ofNullable(eventType);
    }

    /**
     * Extend this Commitment's executedBy Events.
     *
     * @param events The additional Events
     * @return An updated Commitment instance
     */
    protected Commitment extendExecutedBy(List<Event> events) {
        Objects.requireNonNull(events);

        List<Event> list = new ArrayList<>(executedBy.size() + events.size());
        list.addAll(executedBy);
        list.addAll(events);
        return this.executedBy(list);
    }

    /**
     * Fulfill or unfulfill this Commitment.
     *
     * @param isFulfilled whether to fulfill or unfunfill
     *
     * @return An updated Commitment instance
     */
    protected abstract Commitment fulfill(boolean isFulfilled);

    /**
     * The Events to associate as executing this Event.
     *
     * @param events The Events to associate with this Commitment
     * @return An updated Commitment instance
     */
    protected abstract Commitment executedBy(List<Event> events);
}
