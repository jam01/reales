package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * The abstract and extended specification of an Economic Resource, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific resource.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class ResourceType {
    protected @Nullable ResourceType type;

    protected ResourceType() {
        this(null);
    }

    protected ResourceType(@Nullable ResourceType type) {
        this.type = type;
    }

    public Optional<ResourceType> type() {
        return Optional.ofNullable(type);
    }
}
