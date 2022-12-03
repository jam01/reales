package com.jam01.reales.distributor.commitments;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Commitment;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.Reservation;
import com.jam01.reales.core.Stockflow;
import com.jam01.reales.core.Value;

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
    public Commitment executedBy(List<Event<? extends Stockflow>> events) {
        return new SalesVAT(provider, receiver, (List<Reservation.Specification>) reservations, events, false);
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
