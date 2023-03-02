package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

/**
 * The abstract and extended specification of an Economic Agent, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific person or unit.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class AgentType {
    private final AgentType type;

    public AgentType(@Nullable AgentType type) {
        this.type = type;
    }

    public AgentType() {
        this(null);
    }

    public AgentType type() {
        return type;
    }
}
