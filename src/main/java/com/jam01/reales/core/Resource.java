package com.jam01.reales.core;

import java.util.Optional;

public abstract class Resource {
    public Optional<ResourceType> specification() {
        return Optional.empty();
    }

}
