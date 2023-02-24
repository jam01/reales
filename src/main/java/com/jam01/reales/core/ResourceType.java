package com.jam01.reales.core;

import com.jam01.reales.core.attributes.UnitOfMeasure;

public abstract class ResourceType {
    public final UnitOfMeasure unit;

    protected ResourceType(UnitOfMeasure unit) {
        this.unit = unit;
    }
}
