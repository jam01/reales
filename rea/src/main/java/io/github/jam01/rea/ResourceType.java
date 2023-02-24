package io.github.jam01.rea;

import io.github.jam01.rea.attributes.UnitOfMeasure;

public abstract class ResourceType {
    public final UnitOfMeasure unit;

    protected ResourceType(UnitOfMeasure unit) {
        this.unit = unit;
    }
}
