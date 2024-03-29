package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class Agent {
    private final @Nullable AgentType type;

    protected Agent(@Nullable AgentType type) {
        this.type = type;
    }

    protected Agent() {
        this(null);
    }

    public Optional<AgentType> type() {
        return Optional.ofNullable(type);
    }
}
