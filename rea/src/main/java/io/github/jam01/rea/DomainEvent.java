package io.github.jam01.rea;

import java.time.Instant;

public abstract class DomainEvent {
    public final Instant occurredOn;

    public DomainEvent() {
        this(Instant.now());
    }

    public DomainEvent(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }
}
