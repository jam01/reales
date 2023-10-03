package io.github.jam01.reales.domain.conversion;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.conversion.Use;
import io.github.jam01.reales.domain.attributes.Attribute;
import io.github.jam01.reales.domain.exchange.FTransfer;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class FUse extends Use {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FUse, Void> invariants;

    public FUse(Resource resource,
                Map<String, Attribute<?>> attributes,
                Function<FUse, Void> invariants) {
        super(resource);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
