package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public abstract class CommitmentType {
    private @Nullable CommitmentType type;

    private final Map<String, Attribute<?>> attributes;
    private final Function<CommitmentType, Void> invariants;

    public CommitmentType() {
        this(null, null, null);
    }

    public CommitmentType(@Nullable CommitmentType type,
                          Map<String, Attribute<?>> attributes,
                          Function<CommitmentType, Void> invariants) {
        this.type = type;

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants != null ? invariants : type1 -> null;

        this.invariants.apply(this);
    }

    public CommitmentType type() {
        return type;
    }

    public Optional<Attribute<?>> attribute(String name) {
        return Optional.ofNullable(attributes.get(name));
    }

    public Collection<Attribute<?>> attributes() {
        return attributes.values();
    }
}
