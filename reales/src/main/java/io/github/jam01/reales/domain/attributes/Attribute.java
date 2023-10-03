package io.github.jam01.reales.domain.attributes;

import io.github.jam01.rea.attributes.Value;

import java.time.temporal.TemporalAmount;
import java.util.Map;
import java.util.function.Function;

public class Attribute<T> {
    public final T value;

    public Attribute(T value) {
        this.value = value;
    }

    public static final class StringAttribute extends Attribute<String> {
        public StringAttribute(String value) {
            super(value);
        }
    }

    public static final class NumberAttribute extends Attribute<Number> {
        public NumberAttribute(Number value) {
            super(value);
        }
    }

    public static final class BooleanAttribute extends Attribute<Boolean> {
        public BooleanAttribute(Boolean value) {
            super(value);
        }
    }

    public static final class ValueAttribute extends Attribute<Value<?>> {
        public ValueAttribute(Value<?> value) {
            super(value);
        }
    }

    public enum Type {
        Number, String, Object, Array
    }

    public static final class Derivation<V> {
        public final boolean isCacheable;
        public final TemporalAmount cacheTTL;
        public final boolean editable;
        public final Function<Object, Attribute<V>> function;

        public Derivation(boolean isCacheable,
                          TemporalAmount cacheTTL,
                          boolean editable,
                          Function<Object, Attribute<V>> function) {
            this.isCacheable = isCacheable;
            this.cacheTTL = cacheTTL;
            this.editable = editable;
            this.function = function;
        }
    }

    public static final class ObjectDefinition extends Definition<Map<String, Object>> {
        public final Map<String, Definition<Object>> attributes;

        public ObjectDefinition(boolean isRequired,
                                boolean isPk,
                                boolean isDerived,
                                Derivation<Map<String, Object>> derivation,
                                Map<String, Definition<Object>> attributes) {
            super(Type.Object, derivation);
            this.attributes = attributes;
        }
    }
}
