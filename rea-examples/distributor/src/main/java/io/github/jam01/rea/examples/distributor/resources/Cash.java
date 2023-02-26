package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;

public class Cash extends CollectionResource<BigDecimal> {
    public final String name;

    public Cash(MoneyType type, String name, BigDecimal quantity) {
        super(type, null, null, Value.of(quantity, type.unit));
        this.name = name;
    }
}
