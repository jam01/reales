package com.jam01.reales.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Resource {
    private final ResourceType type;
    public final List<Group> groups;

    public Resource(ResourceType type, List<Group> groups) {
        this.type = type;
        this.groups = groups != null ? Collections.unmodifiableList(groups) : Collections.emptyList();
    }

    public Resource() {
        this(null, null);
    }

    public Optional<ResourceType> type() {
        return Optional.ofNullable(type);
    }

}
