package io.github.jam01.rea;


public abstract class Stockflow {
    public final Resource resource;

    // TODO: 2/24/23 can we restrict visibility to only conversion and exchange subclasses
    protected Stockflow(Resource resource) {
        this.resource = resource;
    }
}
