package com.jam01.reales.core.aspects.identifier;

public final class RegularExpressionValidator implements Validator {
    private final String regex;

    RegularExpressionValidator(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean validate(Identifier id) {
        return id.value.matches(regex);
    }
}
