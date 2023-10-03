package io.github.jam01.reales.domain;

import io.github.jam01.rea.EventType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FEventType extends EventType {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FEventType, Void> invariants;

    public FEventType() {
        this(null, null, null);
    }

    public FEventType(@Nullable EventType type,
                      Map<String, Attribute<?>> attributes,
                      Function<FEventType, Void> invariants) {
        super(type);
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
