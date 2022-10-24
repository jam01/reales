package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Event;

import java.util.Collection;

public class Mediator {
    public Collection<Event<Inflow>> increased;
    public Collection<Event<Outflow>> decreased;

}
