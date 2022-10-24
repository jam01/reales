package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;

import java.util.Collection;

public final class Produce extends Stockflow {

    public Produce(Resource resource, Collection<Feature> features) {
        super(Direction.INCREASE, resource, features);
    }
}
