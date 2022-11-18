package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.Collection;

public final class Produce extends Stockflow implements HasFeatures {
    private final Collection<Feature> features;

    public Produce(Resource resource, Collection<Feature> features) {
        super(resource);
        this.features = features;
    }

    @Override
    public Collection<Feature> getFeatures() {
        return features;
    }
}
