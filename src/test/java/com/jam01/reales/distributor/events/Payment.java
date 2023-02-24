package com.jam01.reales.distributor.events;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.exchange.Transfer;

import java.util.List;

public class Payment extends Event<Transfer> {
    public Payment(Agent provider, Agent receiver, List<Transfer> stockflow) {
        super(provider, receiver, stockflow);
    }
}
