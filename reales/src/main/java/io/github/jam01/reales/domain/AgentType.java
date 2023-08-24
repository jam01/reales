package io.github.jam01.reales.domain;

import com.fasterxml.jackson.core.JsonParser;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * The abstract and extended specification of an Economic Agent, in which its essential, grouped, or standardized
 * properties can be designated without attachment to a specific person or unit.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class AgentType {
    private @Nullable AgentType type;

    private final Map<String, Attribute<?>> attributes;
    private final Function<AgentType, Void> invariants;

    public AgentType() {
        this(null, null, null);
    }

    public AgentType(@Nullable AgentType type, 
                        Map<String, Attribute<?>> attributes,
                        Function<AgentType, Void> invariants) {
        this.type = type;
        
        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants != null ? invariants : type1 -> null;

        this.invariants.apply(this);

        JsonParser p;
        p.
    }

    /**
     * The super type of AgentType
     *
     * @return Optional[AgentType] of this AgentType
     */
    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }

    public Optional<Attribute<?>> attribute(String name) {
        return Optional.ofNullable(attributes.get(name));
    }

    public Map<String, Attribute<?>> attributes() {
        return attributes;
    }
}
