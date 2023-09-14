package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * The abstract and extended specification of an Economic Agent, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific person or unit.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class AgentType {
    protected @Nullable AgentType type;

    protected AgentType() {
        this(null);
    }

    protected AgentType(@Nullable AgentType type) {
        this.type = type;
    }

    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }
}
