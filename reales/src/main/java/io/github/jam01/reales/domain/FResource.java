package io.github.jam01.reales.domain;

import io.github.jam01.rea.Group;
import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FResource extends Resource {
    protected final Map<String, Attribute<?>> attributes;
    protected final Function<FResource, Void> invariants;

    public FResource(@Nullable ResourceType type, List<? extends Group> groups, List<? extends Resource> components,
                     Map<String, Attribute<?>> attributes,
                     Function<FResource, Void> invariants) {
        super(type, groups, components);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) this.invariants.apply(this);
    }
}
