package io.github.jam01.reales.domain;

import io.github.jam01.rea.AgentType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FAgent extends io.github.jam01.rea.Agent {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FAgent, Void> invariants;

    public FAgent() {
        this(null, null, null);
    }

    public FAgent(@Nullable AgentType type,
                  Map<String, Attribute<?>> attributes,
                  Function<FAgent, Void> invariants) {
        super(type);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
