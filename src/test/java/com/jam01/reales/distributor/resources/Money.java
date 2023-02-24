package com.jam01.reales.distributor.resources;

import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.attributes.UnitOfMeasure;

public class Money extends ResourceType {
    public final String name;

    public Money(String name, UnitOfMeasure unit) {
        super(unit);
        this.name = name;
    }
}
