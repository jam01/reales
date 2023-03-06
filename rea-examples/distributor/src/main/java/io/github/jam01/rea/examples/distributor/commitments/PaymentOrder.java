package io.github.jam01.rea.examples.distributor.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;
import java.util.List;

import static io.github.jam01.rea.examples.distributor.commitments.SalesOrder.matchBySum;

public class PaymentOrder extends Commitment {
    public PaymentOrder(Agent provider, Agent receiver,
                        List<? extends Reservation> reservations) {
        super(provider, receiver, reservations);
    }

    protected PaymentOrder(Agent provider, Agent receiver,
                           List<? extends Reservation> reservations,
                           List<Event> executedBy,
                           boolean isFulfilled) {
        super(null, provider, receiver, reservations, null, executedBy, isFulfilled);
        // possible improvements:
        // - assert each reservation.type is MoneyType
        // - assert each reservation.type is DollarMoney instance
        // - assert each reservation.quantity is BigDecimal
    }

    @Override
    public PaymentOrder execute(List<Event> events) {
        if (isFulfilled) throw new IllegalStateException("Cannot modify execute events after order is fulfilled");
        boolean isNowFulfilled = matchBySum(((List<Reservation.Specification>) reservations), events);

        return new PaymentOrder(provider, receiver, reservations, events, isNowFulfilled);
    }

    @Override
    public PaymentOrder fulfill(boolean isFulfilled) {
        throw new UnsupportedOperationException("PaymentOrder can only be fulfilled by Payment Events matching its Commitments");
    }

    // sum(reservations.quantity)
    // assuming all the reservations use the same ResType (e.g.: DollarMoney)
    // and all the values passed are BigDecimals
    public Value<BigDecimal> total() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((Reservation.Specification) reservation); // should be money
            subtotal = subtotal.add(((BigDecimal) line.quantity().value()));
        }

        return new Value<>(subtotal, ((Reservation.Specification) reservations.get(0)).quantity().unit());
    }
}
