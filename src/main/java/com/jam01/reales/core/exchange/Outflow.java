package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.Collection;

public final class Outflow extends Stockflow.Decrement implements HasRights {
    private final Collection<Right> rights;

    public Outflow(Resource resource, Collection<Right> rights) {
        super(resource);
        this.rights = rights;
    }

    @Override
    public Collection<Right> getRights() {
        return rights;
    }
}
