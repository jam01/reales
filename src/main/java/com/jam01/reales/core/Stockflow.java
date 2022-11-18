package com.jam01.reales.core;


public abstract class Stockflow {
    public final Resource resource;

    protected Stockflow(Resource resource) {
        this.resource = resource;
    }
}
