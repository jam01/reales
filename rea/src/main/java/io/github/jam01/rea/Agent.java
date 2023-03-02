package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

/**
 * Economic Agents are identifiable parties (1) with discretionary power to use or dispose of economic resources or (2)
 * who are responsible for subordinates’ use or disposition.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Agent {
    private final @Nullable AgentType type;

    protected Agent(@Nullable AgentType type) {
        this.type = type;
    }

    protected Agent() {
        this(null);
    }

    /**
     * The type of Agent
     *
     * @return Optional[AgentType] of this Agent
     */
    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }
}
