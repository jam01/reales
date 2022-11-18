package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.Collection;

public final class Use extends Stockflow implements HasFeatures {
    private final Collection<Feature> features;

    public Use(Resource resource, Collection<Feature> features) {
        super(resource);
        this.features = features;
    }

    @Override
    public Collection<Feature> getFeatures() {
        return features;
    }
}
