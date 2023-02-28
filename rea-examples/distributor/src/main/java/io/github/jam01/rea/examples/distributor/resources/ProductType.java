package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;

public class ProductType extends ResourceType {
    public final String name;
    public final UnitOfMeasure unit;
    public final Value<BigDecimal> price;
    public final boolean hasVAT;
    public final int percentageVAT;

    public ProductType(String name, UnitOfMeasure unit, Value<BigDecimal> price, boolean hasVAT, int percentageVAT) {
        this.unit = unit;
        this.name = name;
        this.price = price;
        this.hasVAT = hasVAT;
        this.percentageVAT = percentageVAT;
    }

    public ProductType(String name, UnitOfMeasure unit, Value<BigDecimal> price) {
        this(name, unit, price, true, 15);
    }
}
