package com.jam01.reales.core;

import java.util.Objects;

public class UnitOfMeasure {
    public final String Name;
    public final String symbol;

    public UnitOfMeasure(String Name, String symbol) {
        this.Name = Name;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UnitOfMeasure) obj;
        return Objects.equals(this.Name, that.Name) &&
                Objects.equals(this.symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, symbol);
    }

    @Override
    public String toString() {
        return "UnitOfMeasure[" +
                "Name=" + Name + ", " +
                "symbol=" + symbol + ']';
    }
}
