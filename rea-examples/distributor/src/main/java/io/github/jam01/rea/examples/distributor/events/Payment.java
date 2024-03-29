package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.exchange.Transfer;

import java.util.List;

public class Payment extends Event<Transfer> {
    public Payment(Agent provider, Agent receiver, List<Transfer> stockflow) {
        super(provider, receiver, stockflow);
    }

    @Override
    protected Event<Transfer> withStockflow(List<Transfer> stockflow1) {
        throw new UnsupportedOperationException("This implementation does not support adding stockflow after creating the Event");
    }
}
