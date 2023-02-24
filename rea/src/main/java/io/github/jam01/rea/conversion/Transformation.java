package io.github.jam01.rea.conversion;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.Stockflow;

public abstract class Transformation extends Stockflow {

    protected Transformation(Resource resource) {
        super(resource);
    }
}
