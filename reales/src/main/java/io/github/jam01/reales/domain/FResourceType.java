package io.github.jam01.reales.domain;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FResourceType extends ResourceType {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FResourceType, Void> invariants;

    public FResourceType() {
        this(null, null, null);
    }

    public FResourceType(@Nullable ResourceType type,
                         Map<String, Attribute<?>> attributes,
                         Function<FResourceType, Void> invariants) {
        super(type);
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
