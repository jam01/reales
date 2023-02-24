package io.github.jam01.rea.conversion;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.Stockflow;

import java.util.Collections;
import java.util.List;

public abstract class Transformation extends Stockflow {
    public final List<Feature> features;

    protected Transformation(Resource resource, List<Feature> features) {
        super(resource);
        this.features = Collections.unmodifiableList(features);
    }
}
