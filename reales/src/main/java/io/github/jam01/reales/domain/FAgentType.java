package io.github.jam01.reales.domain;

import io.github.jam01.rea.AgentType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FAgentType extends io.github.jam01.rea.AgentType {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FAgentType, Void> invariants;

    public FAgentType() {
        this(null, null, null);
    }

    public FAgentType(@Nullable AgentType type,
                      Map<String, Attribute<?>> attributes,
                      Function<FAgentType, Void> invariants) {
        super(type);
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
