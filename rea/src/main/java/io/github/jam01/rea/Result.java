package io.github.jam01.rea;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Result<T>(T value, List<DomainEvent> events) {
    public Result(T value) {
        this(value, Collections.emptyList());
    }

    public Result {
        Objects.requireNonNull(value);
        events = events != null ? Collections.unmodifiableList(events) : Collections.emptyList();
    }
}
