package com.jam01.reales.core;


import java.util.Objects;
import java.util.Optional;

public class Reservation {
    private final Resource resource;
    private final ResourceType resourceType;

    public final Value quantity;

    public Reservation(Resource resource) {
        this.resource = resource;
        this.resourceType = null;
        this.quantity = null;
    }

    public Reservation(ResourceType resourceType, double quantity) {
        this.resourceType = resourceType;
        this.quantity = Value.of(quantity, resourceType.unit);
        this.resource = null;
    }

    public Optional<Resource> resource() {
        return Optional.ofNullable(resource);
    }

    public Optional<ResourceType> resourceType() {
        return Optional.ofNullable(resourceType);
    }

    public boolean isAllocated() {
        return resource != null;
    }

    public Reservation allocated(Resource resource) {
        if (resourceType != null) {
            var allocType = resource.type();
            if (allocType.isEmpty()) throw new IllegalArgumentException("Cannot allocate a resource without a type if a type was previously specified");
            if (!resourceType.equals(allocType.get())) throw new IllegalArgumentException("Cannot allocate a resource of a different type as reserved");
        }
        return new Reservation(resource);
    }
}
