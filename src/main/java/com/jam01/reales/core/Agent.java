package com.jam01.reales.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class Agent {
    private final Collection<Resource> resources = new ArrayList<>(0);
    private final AgentType specification;

    public Agent(AgentType specification) {
        this.specification = specification;
    }

    public Agent() {
        this.specification = null;
    }

    public Collection<Resource> resources() {
        return resources;
    }

    public Optional<AgentType> specification() {
        return Optional.ofNullable(specification);
    }

    public void assignResource(Resource... res) {
        // TODO: 5/20/20 validate non null
        Collections.addAll(resources, res);
    }
}
