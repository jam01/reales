package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Event<T extends Stockflow> {
    private final @Nullable EventType type;
    public final List<T> stockflow;
    public final Agent provider;
    public final Agent receiver;

    public Optional<EventType> type() {
        return Optional.ofNullable(type);
    }

    protected Event(@Nullable EventType type, Agent provider, Agent receiver, List<T> stockflow) {
        Objects.requireNonNull(provider);
        Objects.requireNonNull(receiver);

        this.type = type;
        this.stockflow = stockflow != null ? Collections.unmodifiableList(stockflow) : Collections.emptyList();
        this.provider = provider;
        this.receiver = receiver;
    }

    protected Event(Agent provider, Agent receiver, List<T> stockflow) {
        this(null, provider, receiver, stockflow);
    }

    protected abstract Event<T> withStockflow(List<T> stockflow1);

    protected Event<T> extendStockflow(List<T> stockflow1) {
        Objects.requireNonNull(stockflow1);

        List<T> list = new ArrayList<>(stockflow1.size() + stockflow1.size());
        list.addAll(stockflow);
        list.addAll(stockflow1);

        return this.withStockflow(list);
    }
}
