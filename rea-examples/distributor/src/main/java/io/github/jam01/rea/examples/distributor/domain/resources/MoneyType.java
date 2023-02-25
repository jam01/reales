package io.github.jam01.rea.examples.distributor.domain.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.UnitOfMeasure;

import java.math.BigDecimal;

public class MoneyType extends ResourceType {
    public final String name;
    public final UnitOfMeasure<BigDecimal> unit;

    // only allow decimal values
    public MoneyType(String name, UnitOfMeasure<BigDecimal> unit) {
        this.name = name;
        this.unit = unit;
    }
}
