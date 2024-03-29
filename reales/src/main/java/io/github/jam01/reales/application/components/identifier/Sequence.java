package io.github.jam01.reales.application.components.identifier;

public final class Sequence implements Generator {
    private int seed;
    private final int step;

    Sequence(int seed, int step) {
        this.seed = seed;
        this.step = step;
    }

    @Override
    public synchronized Identifier next() {
        seed = seed + step;
        return new Identifier(String.valueOf(seed));
    }
}
