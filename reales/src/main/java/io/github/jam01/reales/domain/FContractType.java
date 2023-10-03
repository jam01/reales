package io.github.jam01.reales.domain;

import io.github.jam01.rea.ContractType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public final class FContractType extends ContractType {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FContractType, Void> invariants;

    public FContractType() {
        this(null, null, null);
    }

    public FContractType(@Nullable ContractType type,
                         Map<String, Attribute<?>> attributes,
                         Function<FContractType, Void> invariants) {
        super(type);
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
