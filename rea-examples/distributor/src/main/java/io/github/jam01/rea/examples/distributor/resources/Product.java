package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;

public class Product extends ResourceType {
    public final String name;
    public final Value price;
    public final boolean hasVAT;
    public final int percentageVAT;

    public Product(String name, UnitOfMeasure unit, Value price, boolean hasVAT, int percentageVAT) {
        super(unit);
        this.name = name;
        this.price = price;
        this.hasVAT = hasVAT;
        this.percentageVAT = percentageVAT;
    }

    public Product(String name, UnitOfMeasure unit, Value price) {
        super(unit);
        this.name = name;
        this.price = price;
        this.hasVAT = true;
        this.percentageVAT = 15;
    }
}
