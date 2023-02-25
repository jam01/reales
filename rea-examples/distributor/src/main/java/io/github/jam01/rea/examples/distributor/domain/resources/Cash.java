package io.github.jam01.rea.examples.distributor.domain.resources;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;

public class Cash extends Resource {
    public final BigDecimal amount;
    public final String name;

    public Cash(ResourceType specification, String name, BigDecimal amount) {
        super(specification, null, null);
        this.name = name;
        this.amount = amount;
    }
}
