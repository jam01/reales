package io.github.jam01.reales.domain;


import io.github.jam01.reales.domain.attributes.Attribute;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

public class NullObjects {
    public static final Function<Object, Void> NO_INVARIANTS = attrs -> null;
    public static final Map<String, Attribute<?>> NO_ATTRIBUTES = Collections.emptyMap();
}
