package io.github.jam01.reales.domain;

import java.time.Instant;

public class DomainEvent {
    public final Instant occurredOn;


    public DomainEvent(Instant occurredOn) {
        this.occurredOn = occurredOn;
    }
}
