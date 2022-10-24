package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Event;

import java.util.Collection;

public class Mediator {
    public Collection<Event<Produce>> produced;
    public Collection<Event<DecreaseStockflow>> decreased;
}
