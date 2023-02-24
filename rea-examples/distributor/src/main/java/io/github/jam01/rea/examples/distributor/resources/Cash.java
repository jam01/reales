package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

public class Cash extends Resource {
    public final Value amount;
    public final String name;

    public Cash(ResourceType specification, String name, Value amount) {
        super(specification, null, null);
        this.name = name;
        this.amount = amount;
    }
}
