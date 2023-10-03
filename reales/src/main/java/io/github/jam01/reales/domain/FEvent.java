package io.github.jam01.reales.domain;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.EventType;
import io.github.jam01.rea.Result;
import io.github.jam01.rea.Stockflow;
import org.jspecify.annotations.Nullable;

import java.util.List;

public final class FEvent extends io.github.jam01.rea.Event {
    private FEvent(Agent provider, Agent receiver, List<? extends Stockflow> stockflow) {
        super(provider, receiver, stockflow);
    }

    private FEvent(@Nullable EventType type, Agent provider, Agent receiver, List<? extends Stockflow> stockflow) {
        super(type, provider, receiver, stockflow);
    }

    @Override
    protected Result<? extends io.github.jam01.rea.Event> withStockflow(List<? extends Stockflow> stockflow1) {
        return null;
    }
}
