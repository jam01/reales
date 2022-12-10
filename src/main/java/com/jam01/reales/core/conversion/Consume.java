package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.List;

public final class Consume extends Stockflow implements HasFeatures {
    private final List<Feature> features;

    public Consume(Resource resource, List<Feature> features) {
        super(resource);
        this.features = features;
    }

    @Override
    public List<Feature> getFeatures() {
        return features;
    }
}
