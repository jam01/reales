package com.jam01.reales.core;


import java.util.Optional;

public abstract class Reservation {
    private final Stockflow.Direction direction;
    private final Resource resource;
    private final ResourceType resourceType;

    public Reservation(Stockflow.Direction direction, Resource resource) {
        this.direction = direction;
        this.resource = resource;
        this.resourceType = null;
    }

    public Reservation(Stockflow.Direction direction, ResourceType resourceType) {
        this.direction = direction;
        this.resourceType = resourceType;
        this.resource = null;
    }

    public Stockflow.Direction direction() {
        return direction;
    }

    public Optional<Resource> resource() {
        return Optional.ofNullable(resource);
    }

    public Optional<ResourceType> resourceType() {
        return Optional.ofNullable(resourceType);
    }

    public boolean isAllocated() {
        return resource().isEmpty();
    }

    public abstract Reservation allocated(Reservation reservation, Resource resource);

}
