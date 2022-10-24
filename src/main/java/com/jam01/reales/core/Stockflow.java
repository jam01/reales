package com.jam01.reales.core;


public abstract class Stockflow {
    public final Direction direction;
    public final Resource resource;

    public Stockflow(Direction direction, Resource resource) {
        this.direction = direction;
        this.resource = resource;
    }

    public enum Direction {
        INCREASE, DECREASE
    }
}
