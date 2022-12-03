package com.jam01.reales.distributor;

import com.jam01.reales.core.Reservation;
import com.jam01.reales.core.Value;
import com.jam01.reales.distributor.resources.Product;

public class SalesLine extends Reservation.Specification {
    public final Value price;

    public SalesLine(Product resourceType, double quantity, Value price) {
        super(resourceType, quantity);
        this.price = price;
    }

    public SalesLine(Product resourceType, double quantity) {
        super(resourceType, quantity);
        this.price = resourceType.price;
    }

    public Value amount() {
        return new Value(this.price.value().multiply(quantity.value()), price.unit()); // this.price * this.quantity
    }
}
