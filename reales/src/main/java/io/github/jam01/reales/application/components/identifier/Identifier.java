package io.github.jam01.reales.application.components.identifier;

public final class Identifier {
    public final String value;

    public Identifier(String id) {
        this.value = id;
    }

    private final static Generator uuid = new UUID();

    public static Generator uuid() {
        return uuid;
    }

    public static Sequence sequence(int seed, int step) {
        return new Sequence(seed, step);
    }

    public static Validator regularExpression(String regex) {
        return new RegularExpressionValidator(regex);
    }
}
