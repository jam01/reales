package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.Stockflow;
import io.github.jam01.rea.examples.distributor.agents.Enterprise;
import io.github.jam01.rea.exchange.Transfer;

import java.util.List;

public class Purchase extends Event {

    public Purchase(Agent receiver, List<Transfer> stockflow) {
        super(Enterprise.getInstance(), receiver, stockflow);
    }

    @Override
    protected Event withStockflow(List<? extends Stockflow> stockflow1) {
        throw new UnsupportedOperationException("This implementation does not support adding Stockflow after creating the Event");
    }
}
