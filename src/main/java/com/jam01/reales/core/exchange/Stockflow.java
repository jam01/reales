package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;

import java.util.Collection;

public class Stockflow extends com.jam01.reales.core.Stockflow {
    public final Collection<Right> rights;

    public Stockflow(Direction direction, Resource resource, Collection<Right> rights) {
        super(direction, resource);
        this.rights = rights;
    }
}
