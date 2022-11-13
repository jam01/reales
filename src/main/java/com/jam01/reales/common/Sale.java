package com.jam01.reales.common;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.EventType;
import com.jam01.reales.core.Stockflow;

public class Sale extends Event.Decrement {

    public Sale(Stockflow.Decrement stockflow, Agent provider, Agent receiver) {
        super(stockflow, provider, receiver);
    }
}
