package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Agent;
import io.github.jam01.rea.Event;
import io.github.jam01.rea.exchange.Transfer;

import java.util.List;

public class Delivery extends Event<Transfer> {
    public Delivery(Agent provider, Agent receiver, List<Transfer> stockflow) {
        super(provider, receiver, stockflow);
    }
}
