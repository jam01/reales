package com.jam01.reales.application.components.identifier;

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
