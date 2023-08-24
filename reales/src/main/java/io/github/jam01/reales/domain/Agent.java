package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * Economic Agents are identifiable parties (1) with discretionary power to use or dispose of economic resources or (2)
 * who are responsible for subordinates’ use or disposition.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public class Agent {
    public final @Nullable AgentType type;

    private final Map<String, Attribute<?>> attributes;
    private final Function<Agent, Void> invariants;


    public Agent() {
        this(null, null, null);
    }

    public Agent(@Nullable AgentType type, 
                 Map<String, Attribute<?>> attributes, 
                 Function<Agent, Void> invariants) {        
        this.type = type;
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants != null ? invariants : agent -> null; 
        
        this.invariants.apply(this);
    }

    /**
     * The type of Agent
     *
     * @return Optional[AgentType] of this Agent
     */
    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }
    
    public Optional<Attribute<?>> attribute(String name) {
        return Optional.ofNullable(attributes.get(name));
    }

    public Collection<Attribute<?>> attributes() {
        return attributes.values();
    }
}
