package io.github.jam01.rea.attributes;

import java.util.Objects;

public record UnitOfMeasure(String name, String symbol) {
    public UnitOfMeasure {
        Objects.requireNonNull(name);
        Objects.requireNonNull(symbol);
    }

    public static UnitOfMeasure of(String name, String symbol) {
        return new UnitOfMeasure(name, symbol);
    }
}
