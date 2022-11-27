package com.jam01.reales.distributor.commitments;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Commitment;
import com.jam01.reales.core.Reservation;
import com.jam01.reales.core.Value;
import com.jam01.reales.distributor.SalesLine;
import com.jam01.reales.distributor.resources.Money;
import com.jam01.reales.distributor.resources.Product;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class SalesOrder extends Commitment {
    public final OffsetDateTime createdOn;

    public SalesOrder(Agent provider, Agent receiver, List<SalesLine> reservations, OffsetDateTime createdOn) {
        super(provider, receiver, reservations);

        if (createdOn == null) createdOn = OffsetDateTime.now();
        this.createdOn = createdOn;

        // TODO: 11/27/22 validate all reservations of different resource types
        // TODO: 11/27/22 validate all same unit
    }


    public SalesOrder(Agent provider, Agent receiver, List<SalesLine> reservations) {
        this(provider, receiver, reservations, null);
    }

    public Value subtotal() {
        var subtotal = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            subtotal = subtotal.add(line.amount().value());
        }

        return new Value(subtotal, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    public Value vat() {
        var vat = BigDecimal.ZERO;

        for (Reservation reservation : reservations) {
            var line = ((SalesLine) reservation);
            var product = (Product) line.resourceType().orElseThrow();
            if (product.hasVAT)
                vat = vat.add(line.amount().value().multiply(BigDecimal.valueOf(product.percentageVAT).divide(BigDecimal.valueOf(100)))); // line.amount * (product.percentageVAT / 100)
        }

        return new Value(vat, ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }

    public Value total() {
        return new Value(subtotal().value().add(vat().value()), ((SalesLine) reservations.get(0)).price.unit()); // TODO: 11/27/22 what unit to use?
    }
}
