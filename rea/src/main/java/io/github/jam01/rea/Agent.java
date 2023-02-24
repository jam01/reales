package io.github.jam01.rea;

import java.util.Optional;

public abstract class Agent {
    private final AgentType type;

    protected Agent(AgentType type) {
        this.type = type;
    }

    protected Agent() {
        this.type = null;
    }

    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }

}
