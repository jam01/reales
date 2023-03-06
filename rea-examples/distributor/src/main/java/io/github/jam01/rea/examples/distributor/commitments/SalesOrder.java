package io.github.jam01.rea.examples.distributor.commitments;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.agents.Enterprise;
import io.github.jam01.rea.examples.distributor.events.CollectionTransfer;
import io.github.jam01.rea.examples.distributor.resources.CollectionResource;
import io.github.jam01.rea.examples.distributor.resources.ProductType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.HashMap;
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
                      List<Event> executedBy,
                      boolean isFulfilled,
                      OffsetDateTime createdOn) {
        super(null, enterprise, receiver, reservations, null, executedBy, isFulfilled);

        if (createdOn == null) createdOn = OffsetDateTime.now();
        this.createdOn = createdOn;
        // possible improvements:
        // - assert each reservation.quantity is Integer
        // - assert all reservations are of different ResType
    }


    // sum(reservations.amount)
    // assuming all the reservations use the same ResType (e.g.: DollarMoney)
    // and all the values passed are BigDecimals
    public Value<BigDecimal> subtotal() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            subtotal = subtotal.add(line.amount().value());
        }

        return new Value<>(subtotal, ((SalesLine) reservations.get(0)).price.unit());
    }

    // sum(reservations.amount * reservation.product.percentageVAT / 100)
    // assuming all the reservations use the same ResType (e.g.: DollarMoney)
    // and all the values passed are BigDecimals
    public Value<BigDecimal> vat() {
        var vat = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            var product = (ProductType) line.resourceType();
            if (product.hasVAT)
                vat = vat.add(line.amount().value()
                        .multiply(BigDecimal.valueOf(product.percentageVAT))
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP)); // line.amount * (product.percentageVAT / 100) with two decimals
        }

        return new Value<>(vat, ((SalesLine) reservations.get(0)).price.unit());
    }

    // subtotal + vat
    // assuming all the reservations use the same ResType (e.g.: DollarMoney)
    public Value<BigDecimal> total() {
        return new Value<>(subtotal().value().add(vat().value()), ((SalesLine) reservations.get(0)).price.unit());
    }

    @Override
    public SalesOrder execute(List<Event> events) {
        if (isFulfilled) throw new IllegalStateException("Cannot modify execute events after order is fulfilled");
        boolean isNowFulfilled = matchBySum(((List<SalesLine>) reservations), events);

        return new SalesOrder(receiver, (List<SalesLine>) reservations, events, isNowFulfilled, this.createdOn);
    }

    public static boolean matchBySum(List<? extends Reservation.Specification> reservations, List<Event> events) {
        var isFulfilled = false;
        var typeSum = new HashMap<ResourceType, Double>(); // precision tradeoff

        for (Event event : events) {
            for (Stockflow stockflow : event.stockflow()) {
                CollectionTransfer<?> transfer = ((CollectionTransfer<?>) stockflow);
                var res = ((CollectionResource<?>) transfer.resource());
                var sum = typeSum.get(res.type().orElseThrow());
                if (sum == null)  {
                    typeSum.put(res.type().orElseThrow(), res.quantity().value().doubleValue());
                    continue;
                }

                if (!res.quantity().unit().equals(res.quantity().unit()))
                    throw new IllegalArgumentException("Cannot execute order with Resources in a different unit than reserved");

                typeSum.put(res.type().orElseThrow(), sum + res.quantity().value().doubleValue());
            }

            for (Reservation.Specification reservation : reservations) {
                var sum = typeSum.get(reservation.resourceType());
                if (sum == null || sum < reservation.quantity().value().doubleValue()) {
                    break;
                }

                isFulfilled = true;
            }
        }

        return isFulfilled;
    }

    @Override
    protected final SalesOrder fulfill(boolean isFulfilled) {
        throw new UnsupportedOperationException("SalesOrder can only be fulfilled by Sale Events matching its Commitments");
    }
}
