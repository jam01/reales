package io.github.jam01.reales.domain.attributes;

import java.math.BigDecimal;
import java.util.Objects;

public record Value<T extends Number>(T value, UnitOfMeasure unit) {

    public Value {
        Objects.requireNonNull(value);
        Objects.requireNonNull(unit);
    }

    public static <T extends Number> Value<T> of(T value, UnitOfMeasure unit) {
        return new Value<>(value, unit);
    }
}
