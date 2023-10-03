package io.github.jam01.reales.domain.attributes;

import java.util.List;
import java.util.Map;

public class Definition<U> {
    public final Attribute.Type type;
    public final Attribute.Derivation<U> derivation;

    public Definition(Attribute.Type type,
                      Attribute.Derivation<U> derivation) {
        this.type = type;
        this.derivation = derivation;
    }

    // TODO: 3/20/23 base on openapi4j
    // https://github.com/openapi4j/openapi4j/blob/master/openapi-schema-validator/src/main/java/org/openapi4j/schema/validator/v3/TypeValidator.java
    public boolean validate(Attribute<U> attr) {
        switch (type) {
            case Number -> {
                return attr.value instanceof Number;
            }
            case String -> {
                return attr.value instanceof String;
            }
            case Object -> {
                return attr.value instanceof Map;
            }
            case Array -> {
                return attr.value instanceof List;
            }
            default -> {
                return false;
            }
        }
    }
}
