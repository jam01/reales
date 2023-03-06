package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.exchange.Transfer;

import java.util.List;

public class Payment extends Event {
    public Payment(Agent provider, Agent receiver, List<Transfer> stockflow) {
        super(provider, receiver, stockflow);
    }

    @Override
    protected Event withStockflow(List<? extends Stockflow> stockflow1) {
        throw new UnsupportedOperationException("This implementation does not support adding stockflow after creating the Event");
    }
}
