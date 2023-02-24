package io.github.jam01.reales.application.components.identifier;

public final class UUID implements Generator {
    @Override
    public Identifier next() {
        return new Identifier(java.util.UUID.randomUUID().toString());
    }
}
