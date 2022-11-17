package com.jam01.reales.core;


import java.util.Optional;

public class Reservation {
    private final Resource resource;
    private final ResourceType resourceType;

    public Reservation(Resource resource) {
        this.resource = resource;
        this.resourceType = null;
    }

    public Reservation(ResourceType resourceType) {
        this.resourceType = resourceType;
        this.resource = null;
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

//    public abstract Reservation allocated(Reservation reservation, Resource resource);

}
