package io.github.jam01.reales.domain.exchange;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.exchange.Transfer;
import io.github.jam01.reales.domain.FAgent;
import io.github.jam01.reales.domain.attributes.Attribute;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class FTransfer extends Transfer {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FTransfer, Void> invariants;

    public FTransfer(Resource resource,
                     Map<String, Attribute<?>> attributes,
                     Function<FTransfer, Void> invariants) {
        super(resource);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
