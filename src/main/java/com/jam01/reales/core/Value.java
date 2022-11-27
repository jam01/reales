package com.jam01.reales.core;

import java.math.BigDecimal;

public record Value(BigDecimal value, UnitOfMeasure unit) {

    public static Value of(double value, UnitOfMeasure unit) {
        return new Value(BigDecimal.valueOf(value), unit);
    }
}
