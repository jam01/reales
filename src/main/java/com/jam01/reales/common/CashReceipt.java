package com.jam01.reales.common;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Event;
import com.jam01.reales.core.Stockflow;

public class CashReceipt extends Event.Increment {

    public CashReceipt(Stockflow.Increment stockflow, Agent provider, Agent receiver) {
        super(stockflow, provider, receiver);
    }
}
