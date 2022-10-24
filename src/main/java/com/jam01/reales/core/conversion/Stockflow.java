package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;

import java.util.Collection;

public class Stockflow extends com.jam01.reales.core.Stockflow {
    public final Collection<Feature> features;

    public Stockflow(Direction direction, Resource resource, Collection<Feature> features) {
        super(direction, resource);
        this.features = features;
    }
}
