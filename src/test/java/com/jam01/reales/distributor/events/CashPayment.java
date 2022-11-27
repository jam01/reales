package com.jam01.reales.distributor.events;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.exchange.Transfer;

import java.util.List;

public class CashPayment extends Event<Transfer> {
    public CashPayment(List<Transfer> stockflow, Agent provider, Agent receiver) {
        super(provider, receiver, stockflow);
    }
}
