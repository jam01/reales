package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

/**
 * The abstract and extended specification of an Economic Event, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific actual occurrence in time.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class EventType {
    protected EventType type;

    protected EventType(@Nullable EventType type) {
        this.type = type;
    }

    protected EventType() {
        this(null);
    }

    public EventType type() {
        return type;
    }
}
