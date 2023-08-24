package io.github.jam01.reales.domain;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Economic Events are classes “of phenomena which reflect changes in scarce means [economic resources] resulting from
 * production, exchange, consumption, and distribution”.
 *
 * @param <T> The type of Stockflow the Event can hold
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Event {
    protected @Nullable EventType type;
    protected List<? extends Stockflow> stockflow;
    protected Agent provider;
    protected Agent receiver;

    protected Event(@Nullable EventType type, Agent provider, Agent receiver, List<? extends Stockflow> stockflow) {
        Objects.requireNonNull(provider);
        Objects.requireNonNull(receiver);

        this.type = type;
        this.stockflow = stockflow != null ? Collections.unmodifiableList(stockflow) : Collections.emptyList();
        this.provider = provider;
        this.receiver = receiver;
    }

    protected Event(Agent provider, Agent receiver, List<? extends Stockflow> stockflow) {
        this(null, provider, receiver, stockflow);
    }

    /**
     * The type of Event
     *
     * @return Optional[EventType] of this Event
     */
    public Optional<EventType> type() {
        return Optional.ofNullable(type);
    }

    public List<? extends Stockflow> stockflow() {
        return stockflow;
    }

    public Agent provider() {
        return provider;
    }

    public Agent receiver() {
        return receiver;
    }

    /**
     * Set the given Stockflow for this Event
     *
     * @param stockflow1 The Stockflow to associate with this Event
     * @return An updated Event instance
     */
    protected abstract Event withStockflow(List<? extends Stockflow> stockflow1);

    /**
     * Extend this Event's Stockflow
     *
     * @param stockflow1 The additional Stockflow
     * @return An updated Event instance
     */
    protected Event extendStockflow(List<? extends Stockflow> stockflow1) {
        Objects.requireNonNull(stockflow1);

        List<Stockflow> list = new ArrayList<>(stockflow1.size() + stockflow1.size());
        list.addAll(stockflow);
        list.addAll(stockflow1);

        return this.withStockflow(list);
    }
}
