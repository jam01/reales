package io.github.jam01.rea.examples.distributor.domain.events;

import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.domain.resources.ProductType;

import java.math.BigDecimal;

public class SalesLine extends Reservation.Specification {
    public final Value<BigDecimal> price;

    public SalesLine(ProductType productType, Value<?> quantity, Value<BigDecimal> price) {
        // assuming that the given quantity is in the same number type as the product's uom
        super(productType, quantity);
        this.price = price;

        if (!quantity.unit().equals(productType.unit)) throw new IllegalArgumentException("quantity's UnitOfMeasure must match productType's UnitOfMeasure");
    }

    public SalesLine(ProductType resourceType, Value<?> quantity) {
        this(resourceType, quantity, resourceType.price);
    }

    public Value<BigDecimal> amount() {
        var qtyDecimal = new BigDecimal(quantity.value().toString());

        return new Value<>(this.price.value().multiply(qtyDecimal), price.unit()); // this.price * this.quantity
    }
}
