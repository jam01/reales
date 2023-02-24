package io.github.jam01.rea;


public abstract class Stockflow {
    public final Resource resource;

    protected Stockflow(Resource resource) {
        this.resource = resource;
    }
}
