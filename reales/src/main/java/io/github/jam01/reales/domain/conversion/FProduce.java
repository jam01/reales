package io.github.jam01.reales.domain.conversion;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.conversion.Produce;
import io.github.jam01.rea.conversion.Transformation;
import io.github.jam01.reales.domain.attributes.Attribute;
import io.github.jam01.reales.domain.exchange.FTransfer;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class FProduce extends Produce {
    private final Map<String, Attribute<?>> attributes;
    private final Function<FProduce, Void> invariants;

    public FProduce(Resource resource,
                    Map<String, Attribute<?>> attributes,
                    Function<FProduce, Void> invariants) {
        super(resource);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
