package com.jam01.reales.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Resource {
    private final ResourceType specification;
    public final List<Group> groups;

    public Resource(ResourceType specification, List<Group> groups) {
        this.specification = specification;

        if (groups != null) groups = Collections.unmodifiableList(groups);
        else groups = Collections.emptyList();
        this.groups = groups;
    }

    public Resource() {
        this(null, null);
    }

    public Optional<ResourceType> type() {
        return Optional.ofNullable(specification);
    }

}
