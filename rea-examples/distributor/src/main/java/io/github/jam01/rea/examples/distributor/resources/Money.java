package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.UnitOfMeasure;

public class Money extends ResourceType {
    public final String name;

    public Money(String name, UnitOfMeasure unit) {
        super(unit);
        this.name = name;
    }
}
