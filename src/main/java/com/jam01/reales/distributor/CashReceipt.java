package com.jam01.reales.distributor;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.Stockflow;

import java.util.List;

public class CashReceipt extends Event.Increment {

    public CashReceipt(List<Stockflow.Increment> stockflow, Agent provider, Agent receiver) {
        super(stockflow, provider, receiver);
    }
}
