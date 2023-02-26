package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.examples.distributor.agents.Enterprise;

import java.util.List;

public class Sale extends Event<CollectionTransfer<?>> {

    public Sale(Agent receiver, List<CollectionTransfer<?>> stockflow) {
        super(Enterprise.getInstance(), receiver, stockflow);
    }
}
