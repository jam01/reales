package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.Collections;
import java.util.List;

public abstract class Transformation extends Stockflow {
    public final List<Feature> features;

    protected Transformation(Resource resource, List<Feature> features) {
        super(resource);
        this.features = Collections.unmodifiableList(features);
    }
}
