package com.jam01.reales.core;

public abstract class ResourceType {
    public final UnitOfMeasure unit;

    protected ResourceType(UnitOfMeasure unit) {
        this.unit = unit;
    }
}
