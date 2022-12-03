package com.jam01.reales.distributor.commitments;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Commitment;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.Reservation;
import com.jam01.reales.core.Stockflow;
import com.jam01.reales.core.Value;
import com.jam01.reales.distributor.SalesLine;

import java.math.BigDecimal;
import java.util.List;

public class PaymentOrder extends Commitment {
    public PaymentOrder(Agent provider, Agent receiver,
                        List<? extends Reservation> reservations) {
        super(provider, receiver, reservations);
    }

    protected PaymentOrder(Agent provider, Agent receiver,
                           List<? extends Reservation> reservations,
                           List<Event<? extends Stockflow>> executedBy,
                           boolean isFulfilled) {
        super(null, provider, receiver, reservations, null, executedBy, isFulfilled);
    }

    @Override
    public PaymentOrder executedBy(List<Event<? extends Stockflow>> events) {
        return new PaymentOrder(provider, receiver, reservations, events, isFulfilled);
    }


    // sum(reservations.quantity)
    public Value total() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((Reservation.Specification) reservation); // should be money
            subtotal = subtotal.add(line.quantity.value());
        }

        return new Value(subtotal, ((Reservation.Specification) reservations.get(0)).resourceType.unit); // TODO: 11/27/22 what unit to use?
    }

}
