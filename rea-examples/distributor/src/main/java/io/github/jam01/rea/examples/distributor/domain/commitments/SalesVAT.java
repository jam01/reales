package io.github.jam01.rea.examples.distributor.domain.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.domain.agents.Enterprise;

import java.math.BigDecimal;
import java.util.List;

public class SalesVAT extends Commitment {
    public SalesVAT(Agent receiver, List<Reservation.Specification> reservations) {
        super(Enterprise.getInstance(), receiver, reservations);
    }

    protected SalesVAT(Agent receiver,
                    List<Reservation.Specification> reservations,
                    List<Event<? extends Stockflow>> executedBy,
                    boolean isFulfilled) {
        super(null, Enterprise.getInstance(), receiver, reservations, null, executedBy, isFulfilled);
        // possible improvements:
        // - only allow a single reservation
        // - assert reservations[0].type is MoneyType
        // - assert reservations[0].type is DollarMoney instance
        // - assert reservations[0].quantity is BigDecimal
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesVAT executedBy(List<Event<? extends Stockflow>> events) {
        return new SalesVAT(receiver, (List<Reservation.Specification>) reservations, events, isFulfilled);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesVAT fulfilled(boolean isFulfilled) {
        return new SalesVAT(receiver, (List<Reservation.Specification>) reservations, executedBy, isFulfilled);
    }

    // sum(reservations.quantity)
    // assuming all the reservations use the same ResType (e.g.: DollarMoney)
    // and all the values passed are BigDecimals
    public Value<BigDecimal> total() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((Reservation.Specification) reservation);
            subtotal = subtotal.add(((BigDecimal) line.quantity.value()));
        }

        return new Value<>(subtotal, ((UnitOfMeasure<BigDecimal>) ((Reservation.Specification) reservations.get(0)).quantity.unit()));
    }
}
