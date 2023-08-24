package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.commands.Command;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OperationResult<T> {
    public final T object;
    public final List<Command> commands;
    public final List<DomainEvent> events;

    public OperationResult(T object) {
        this(object, null, null);
    }

    public OperationResult(T object, List<Command> commands, List<DomainEvent> events) {
        Objects.requireNonNull(object);
        
        this.object = object;
        this.commands = commands != null ? Collections.unmodifiableList(commands) : Collections.emptyList();
        this.events = events != null ? Collections.unmodifiableList(events) : Collections.emptyList();
    }
}
