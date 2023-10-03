package io.github.jam01.reales.domain;

import io.github.jam01.rea.CommitmentType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FCommitmentType extends io.github.jam01.rea.CommitmentType {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FCommitmentType, Void> invariants;

    public FCommitmentType() {
        this(null, null, null);
    }

    public FCommitmentType(@Nullable CommitmentType type,
                           Map<String, Attribute<?>> attributes,
                           Function<FCommitmentType, Void> invariants) {
        super(type);
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
