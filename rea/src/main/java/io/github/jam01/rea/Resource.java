package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Economic Resources are groups of objects that (1) are scarce and have utility and (2) are under the control of an
 * economic agent.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Resource {
    public final List<Group> groups;
    public final List<? extends Resource> components;
    private final @Nullable ResourceType type;

    public Resource(@Nullable ResourceType type, List<Group> groups, List<? extends Resource> components) {
        this.type = type;
        this.groups = groups != null ? Collections.unmodifiableList(groups) : Collections.emptyList();
        this.components = components != null ? Collections.unmodifiableList(components) : Collections.emptyList();
    }

    public Resource() {
        this(null, Collections.emptyList(), Collections.emptyList());
    }

    /**
     * The type of Resource
     *
     * @return Optional[ResourceType] of this Resource
     */
    public Optional<ResourceType> type() {
        return Optional.ofNullable(type);
    }

}
