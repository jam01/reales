package io.github.jam01.reales.domain.conversion;

import io.github.jam01.reales.domain.Resource;
import io.github.jam01.reales.domain.Stockflow;

public abstract class Transformation extends Stockflow {

    protected Transformation(Resource resource) {
        super(resource);
    }
}
