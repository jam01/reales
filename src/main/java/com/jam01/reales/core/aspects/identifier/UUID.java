package com.jam01.reales.core.aspects.identifier;

public final class UUID implements Generator {
    @Override
    public Identifier next() {
        return new Identifier(java.util.UUID.randomUUID().toString());
    }
}
