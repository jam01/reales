package com.jam01.reales.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public abstract class Agent {
    private final AgentType specification;

    protected Agent(AgentType specification) {
        this.specification = specification;
    }

    protected Agent() {
        this.specification = null;
    }

    public Optional<AgentType> specification() {
        return Optional.ofNullable(specification);
    }

}
