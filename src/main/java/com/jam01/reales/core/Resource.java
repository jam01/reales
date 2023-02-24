package com.jam01.reales.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Resource {
    private final ResourceType type;
    public final List<Group> groups;
    public final List<? extends Resource> components;

    public Resource(ResourceType type, List<Group> groups, List<? extends Resource> components) {
        this.type = type;
        this.groups = groups != null ? Collections.unmodifiableList(groups) : Collections.emptyList();
        this.components = components != null ? Collections.unmodifiableList(components) : Collections.emptyList();
    }

    public Resource() {
        this(null, null, null);
    }

    public Optional<ResourceType> type() {
        return Optional.ofNullable(type);
    }

}
