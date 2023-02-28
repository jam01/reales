package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.UnitOfMeasure;

import java.math.BigDecimal;

public class MoneyType extends ResourceType {
    public final String name;
    public final UnitOfMeasure unit;

    // only allow decimal values
    public MoneyType(String name, UnitOfMeasure unit) {
        this.name = name;
        this.unit = unit;
    }
}
