package io.github.jam01.rea.examples.distributor.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;
import java.util.List;

public class SalesVAT extends Commitment {
    public SalesVAT(Agent provider, Agent receiver, List<Reservation> reservations) {
        super(provider, receiver, reservations);
    }

    protected SalesVAT(Agent provider, Agent receiver,
                    List<Reservation.Specification> reservations,
                    List<Event<? extends Stockflow>> executedBy,
                    boolean isFulfilled) {
        super(null, provider, receiver, reservations, null, executedBy, isFulfilled);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesVAT executedBy(List<Event<? extends Stockflow>> events) {
        return new SalesVAT(provider, receiver, (List<Reservation.Specification>) reservations, events, isFulfilled);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesVAT fulfilled(boolean isFulfilled) {
        return new SalesVAT(provider, receiver, (List<Reservation.Specification>) reservations, executedBy, isFulfilled);
    }

    // sum(reservations.amount)
    public Value total() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((Reservation.Specification) reservation); // should be money
            subtotal = subtotal.add(line.quantity.value());
        }

        return new Value(subtotal, ((Reservation.Specification) reservations.get(0)).resourceType.unit); // TODO: 11/27/22 what unit to use?
    }
}
