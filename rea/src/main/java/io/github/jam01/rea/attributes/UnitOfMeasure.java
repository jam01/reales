package io.github.jam01.rea.attributes;

import java.util.Objects;

public record UnitOfMeasure<T extends Number>(String name, String symbol, Class<T> number) {
    public UnitOfMeasure {
        Objects.requireNonNull(name);
        Objects.requireNonNull(symbol);
        Objects.requireNonNull(number);
    }

    public static <T extends Number> UnitOfMeasure<T> of(String name, String symbol, Class<T> number) {
        return new UnitOfMeasure<>(name, symbol, number);
    }
}
