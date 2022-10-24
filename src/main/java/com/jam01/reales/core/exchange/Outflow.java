package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;

import java.util.Collection;

public final class Outflow extends Stockflow {
    public Outflow(Resource resource, Collection<Right> rights) {
        super(Direction.DECREASE, resource, rights);
    }
}
