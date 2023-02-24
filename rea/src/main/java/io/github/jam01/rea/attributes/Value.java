package io.github.jam01.rea.attributes;

import java.math.BigDecimal;

public record Value(BigDecimal value, UnitOfMeasure unit) {

    public static Value of(double value, UnitOfMeasure unit) {
        return new Value(BigDecimal.valueOf(value), unit);
    }
}
