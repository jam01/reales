package io.github.jam01.rea.examples.distributor.domain.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.domain.agents.Enterprise;
import io.github.jam01.rea.examples.distributor.domain.events.SalesLine;
import io.github.jam01.rea.examples.distributor.domain.resources.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

public class SalesOrder extends Commitment {
    private static final Agent enterprise = Enterprise.getInstance();
    public final OffsetDateTime createdOn;

    public SalesOrder(Agent receiver,
                      List<SalesLine> reservations) {
        this(receiver, reservations, null, false, null);
    }

    protected SalesOrder(Agent receiver,
                      List<SalesLine> reservations,
                      List<Event<? extends Stockflow>> executedBy,
                      boolean isFulfilled,
                      OffsetDateTime createdOn) {
        super(null, enterprise, receiver, reservations, null, executedBy, isFulfilled);

        if (createdOn == null) createdOn = OffsetDateTime.now();
        this.createdOn = createdOn;

        // TODO: 11/27/22 validate all reservations of different resource types
        // TODO: 11/27/22 validate all same unit
    }


    // sum(reservations.amount)
    public Value<BigDecimal> subtotal() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            subtotal = subtotal.add(line.amount().value());
        }

        return new Value<>(subtotal, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    // sum(reservations.amount * reservation.product.percentageVAT)
    public Value<BigDecimal> vat() {
        var vat = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            var product = (ProductType) line.resourceType;
            if (product.hasVAT)
                vat = vat.add(line.amount().value()
                        .multiply(BigDecimal.valueOf(product.percentageVAT))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)); // line.amount * (product.percentageVAT / 100) with two decimals
        }

        return new Value<>(vat, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    // subtotal + vat
    public Value<BigDecimal> total() {
        return new Value<>(subtotal().value().add(vat().value()), ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    @Override
    public SalesOrder executedBy(List<Event<? extends Stockflow>> events) {
        return new SalesOrder(receiver, (List<SalesLine>) reservations, events, isFulfilled, this.createdOn);
    }

    @Override
    public SalesOrder fulfilled(boolean isFulfilled) {
        return new SalesOrder(receiver, (List<SalesLine>) reservations, executedBy, isFulfilled, this.createdOn);
    }
}
