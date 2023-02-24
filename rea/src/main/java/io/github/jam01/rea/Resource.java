package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Resource {
    private final @Nullable ResourceType type;
    public final List<Group> groups;
    public final List<? extends Resource> components;

    public Resource(@Nullable ResourceType type, List<Group> groups, List<? extends Resource> components) {
        this.type = type;
        this.groups = groups != null ? Collections.unmodifiableList(groups) : Collections.emptyList();
        this.components = components != null ? Collections.unmodifiableList(components) : Collections.emptyList();
    }

    public Resource() {
        this(null, Collections.emptyList(), Collections.emptyList());
    }

    public Optional<ResourceType> type() {
        return Optional.ofNullable(type);
    }

}
