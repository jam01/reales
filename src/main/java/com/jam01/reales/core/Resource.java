package com.jam01.reales.core;

import java.util.Optional;

public abstract class Resource {
    private final ResourceType specification;

    public Resource(ResourceType specification) {
        this.specification = specification;
    }

    public Optional<ResourceType> specification() {
        return Optional.ofNullable(specification);
    }

}
