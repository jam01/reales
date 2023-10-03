package io.github.jam01.reales.domain;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.CommitmentType;
import io.github.jam01.rea.EventType;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Result;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class FCommitment extends io.github.jam01.rea.Commitment {
    // TODO: 3/19/23 withReservations
    // TODO: 3/19/23 executedBy
    // TODO: 3/19/23 test method for fulfill, withReservations, executedBy
    public final Map<String, Attribute<?>> attributes;
    private final Function<FCommitment, Void> invariants;
    private final BiFunction<FCommitment, Boolean, Result<FCommitment>> fulfillFun;
    private final BiFunction<FCommitment, List<? extends Event>, Result<FCommitment>> execFun;

    public FCommitment(@Nullable CommitmentType commitmentType,
                       Agent provider, Agent receiver,
                       List<? extends Reservation> reservations,
                       @Nullable EventType eventType,
                       List<? extends Event> executedBy,
                       boolean isFulfilled,
                       Map<String, Attribute<?>> attributes,
                       Function<FCommitment, Void> invariants,
                       BiFunction<FCommitment, Boolean, Result<FCommitment>> fulfillFun,
                       BiFunction<FCommitment, List<? extends Event>, Result<FCommitment>> execFun) {
        super(commitmentType, provider, receiver, reservations, eventType, executedBy, isFulfilled);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;
        
        if (invariants != null) invariants.apply(this);

        this.fulfillFun = fulfillFun != null ? fulfillFun : (commitment1, bool) ->
                // TODO: 3/19/23 include commitment fulfilled event
                new Result<>(new FCommitment(commitment1.type,
                        commitment1.provider, commitment1.receiver,
                        commitment1.reservations,
                        commitment1.eventType,
                        commitment1.executedBy,
                        bool,
                        commitment1.attributes,
                        commitment1.invariants,
                        commitment1.fulfillFun,
                        commitment1.execFun));
        this.execFun = execFun != null ? execFun : (commitment1, events) ->
                // TODO: 3/19/23 include commitment executed event, potentially commitment fulfilled
                new Result<>(new FCommitment(commitment1.type,
                        commitment1.provider, commitment1.receiver,
                        commitment1.reservations,
                        commitment1.eventType,
                        events,
                        commitment1.isFulfilled,
                        commitment1.attributes,
                        commitment1.invariants,
                        commitment1.fulfillFun,
                        commitment1.execFun));
    }
    public Result<FCommitment> fulfill(boolean isFulfilled) {
        return fulfillFun.apply(this, isFulfilled);
    }

    public Result<FCommitment> execute(List<? extends Event> events) {
        return execFun.apply(this, events);
    }
}
