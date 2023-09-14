package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * The abstract and extended specification of an Economic Event, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific actual occurrence in time.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class EventType {
    protected @Nullable EventType type;

    protected EventType() {
        this(null);
    }

    protected EventType(@Nullable EventType type) {
        this.type = type;
    }

    public Optional<EventType> type() {
        return Optional.ofNullable(type);
    }
}
