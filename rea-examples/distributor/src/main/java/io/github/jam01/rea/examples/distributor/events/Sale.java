package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.examples.distributor.agents.Enterprise;

import java.util.List;

public class Sale extends Event {

    public Sale(Agent receiver, List<CollectionTransfer<?>> stockflow) {
        super(Enterprise.getInstance(), receiver, stockflow);
    }

    @Override
    protected Event withStockflow(List<? extends Stockflow> stockflow1) {
        throw new UnsupportedOperationException("This implementation does not support adding stockflow after creating the Event");
    }
}
