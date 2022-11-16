package com.jam01.reales.core;

import java.math.BigDecimal;

public class Value {
    private final BigDecimal value;
    private final UnitOfMeasure unit;

    public Value(BigDecimal value, UnitOfMeasure unit) {
        this.value = value;
        this.unit = unit;
    }

    public BigDecimal getValue() {
        return value;
    }

    public UnitOfMeasure getUnit() {
        return unit;
    }
}
