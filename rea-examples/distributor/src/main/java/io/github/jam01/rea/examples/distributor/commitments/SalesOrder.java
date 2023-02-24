package io.github.jam01.rea.examples.distributor.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.SalesLine;
import io.github.jam01.rea.examples.distributor.resources.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

public class SalesOrder extends Commitment {
    public final OffsetDateTime createdOn;

    public SalesOrder(Agent provider, Agent receiver,
                      List<SalesLine> reservations) {
        this(provider, receiver, reservations, null, false, null);
    }

    protected SalesOrder(Agent provider, Agent receiver,
                      List<SalesLine> reservations,
                      List<Event<? extends Stockflow>> executedBy,
                      boolean isFulfilled,
                      OffsetDateTime createdOn) {
        super(null, provider, receiver, reservations, null, executedBy, isFulfilled);

        if (createdOn == null) createdOn = OffsetDateTime.now();
        this.createdOn = createdOn;

        // TODO: 11/27/22 validate all reservations of different resource types
        // TODO: 11/27/22 validate all same unit
    }


    // sum(reservations.amount)
    public Value subtotal() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            subtotal = subtotal.add(line.amount().value());
        }

        return new Value(subtotal, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    // sum(reservations.amount * reservation.product.percentageVAT)
    public Value vat() {
        var vat = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            var product = (Product) line.resourceType;
            if (product.hasVAT)
                vat = vat.add(line.amount().value()
                        .multiply(BigDecimal.valueOf(product.percentageVAT))
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)); // line.amount * (product.percentageVAT / 100)
        }

        return new Value(vat, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    // subtotal + vat
    public Value total() {
        return new Value(subtotal().value().add(vat().value()), ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesOrder executedBy(List<Event<? extends Stockflow>> events) {
        return new SalesOrder(provider, receiver, (List<SalesLine>) reservations, events, isFulfilled, this.createdOn);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SalesOrder fulfilled(boolean isFulfilled) {
        return new SalesOrder(provider, receiver, (List<SalesLine>) reservations, executedBy, isFulfilled, this.createdOn);
    }
}
