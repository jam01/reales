package io.github.jam01.rea.examples.distributor.domain.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.examples.distributor.domain.agents.Enterprise;
import io.github.jam01.rea.exchange.Transfer;

import java.util.List;

public class Purchase extends Event<Transfer> {

    public Purchase(Agent receiver, List<Transfer> stockflow) {
        super(Enterprise.getInstance(), receiver, stockflow);
    }
}
