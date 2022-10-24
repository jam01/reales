package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;

import java.util.Collection;

public final class Inflow extends Stockflow {
    public Inflow(Resource resource, Collection<Right> rights) {
        super(Direction.INCREASE, resource, rights);
    }
}
