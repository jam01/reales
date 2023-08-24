package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * An agreement to execute an Economic Event in a well-defined future that will result in either an increase of
 * resources or a decrease of resources.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public final class Commitment {
    // TODO: 3/19/23 withReservations
    // TODO: 3/19/23 executedBy
    // TODO: 3/19/23 test method for fulfill, withReservations, executedBy
    public final @Nullable CommitmentType type;
    public final @Nullable EventType eventType;
    public final List<? extends Reservation> reservations;
    public final Agent receiver;
    public final boolean isFulfilled;
    public final List<Event> executedBy;
    public final Map<String, Attribute<?>> attributes;
    public final Agent provider;
    private final Function<Commitment, Void> invariantsFunc;
    private final BiFunction<Commitment, Boolean, OperationResult<Commitment>> fulfillFunc;
    private final BiFunction<Commitment, List<Event>, OperationResult<Commitment>> executeFunc;

    public Commitment(Agent provider, Agent receiver,
                      List<? extends Reservation> reservations) {
        this(null, null, provider, receiver, reservations, Collections.emptyList(), false, null, null, null, null);
    }

    public Commitment(Agent provider, Agent receiver,
                      List<? extends Reservation> reservations,
                      boolean isFulfilled) {
        this(null, null, provider, receiver, reservations, Collections.emptyList(), isFulfilled, null, null, null, null);
    }

    public Commitment(@Nullable CommitmentType commitmentType,
                      @Nullable EventType eventType,
                      Agent provider, Agent receiver,
                      List<? extends Reservation> reservations,
                      List<Event> executedBy,
                      boolean isFulfilled,
                      Map<String, Attribute<?>> attributes,
                      Function<Commitment, Void> invariantsFunc,
                      BiFunction<Commitment, Boolean, OperationResult<Commitment>> fulfillFunc,
                      BiFunction<Commitment, List<Event>, OperationResult<Commitment>> executeFunc) {
        Objects.requireNonNull(provider);
        Objects.requireNonNull(receiver);

        this.type = commitmentType;
        this.provider = provider;
        this.receiver = receiver;
        this.reservations = reservations != null ? Collections.unmodifiableList(reservations) : Collections.emptyList();
        this.eventType = eventType;
        this.executedBy = executedBy != null ? Collections.unmodifiableList(executedBy) : Collections.emptyList();
        this.isFulfilled = isFulfilled;

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariantsFunc = invariantsFunc != null ? invariantsFunc : commitment -> null;
        this.fulfillFunc = fulfillFunc != null ? fulfillFunc : (commitment1, bool) ->
                // TODO: 3/19/23 include commitment fulfilled event
                new OperationResult<>(new Commitment(commitment1.type,
                        commitment1.eventType,
                        commitment1.provider, commitment1.receiver,
                        commitment1.reservations,
                        commitment1.executedBy,
                        bool,
                        commitment1.attributes,
                        commitment1.invariantsFunc,
                        commitment1.fulfillFunc,
                        commitment1.executeFunc));
        this.executeFunc = executeFunc != null ? executeFunc : (commitment1, events) ->
                // TODO: 3/19/23 include commitment executed event, potentially commitment fulfilled
                new OperationResult<>(new Commitment(commitment1.type,
                        commitment1.eventType,
                        commitment1.provider, commitment1.receiver,
                        commitment1.reservations,
                        events,
                        commitment1.isFulfilled,
                        commitment1.attributes,
                        commitment1.invariantsFunc,
                        commitment1.fulfillFunc,
                        commitment1.executeFunc));
        this.invariantsFunc.apply(this);
    }

    /**
     * The type of Commitment.
     *
     * @return Optional[CommitmentType] of this Commitment
     */
    public Optional<CommitmentType> type() {
        return Optional.ofNullable(type);
    }

    /**
     * The type of Event expected to fulfill this Commitment.
     *
     * @return Optional[CommitmentType] of this Commitment
     */
    public Optional<EventType> eventType() {
        return Optional.ofNullable(eventType);
    }

    public List<? extends Reservation> reservations() {
        return reservations;
    }

    public Agent provider() {
        return provider;
    }

    public Agent receiver() {
        return receiver;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public List<Event> executedBy() {
        return executedBy;
    }

    /**
     * Extend this Commitment's execute Events.
     *
     * @param events The additional Events
     * @return An updated Commitment instance
     */
    public OperationResult<Commitment> extendExecutedBy(List<Event> events) {
        Objects.requireNonNull(events);

        List<Event> list = new ArrayList<>(executedBy.size() + events.size());
        list.addAll(executedBy);
        list.addAll(events);

        return this.execute(list);
    }

    /**
     * Fulfill or unfulfill this Commitment.
     *
     * @param isFulfilled whether to fulfill or unfunfill
     * @return An updated Commitment instance
     */
    public OperationResult<Commitment> fulfill(boolean isFulfilled) {
        return fulfillFunc.apply(this, isFulfilled);
    }

    /**
     * Associate with this Commitment the Events that execute it.
     *
     * @param events The Events to associate with this Commitment
     * @return An updated Commitment instance
     */
    public OperationResult<Commitment> execute(List<Event> events) {
        return executeFunc.apply(this, events);
    }

    public Optional<Attribute<?>> attribute(String name) {
        return Optional.ofNullable(attributes.get(name));
    }

    public Collection<Attribute<?>> attributes() {
        return attributes.values();
    }
}
