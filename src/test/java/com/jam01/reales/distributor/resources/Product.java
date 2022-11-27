package com.jam01.reales.distributor.resources;

import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.UnitOfMeasure;
import com.jam01.reales.core.Value;

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
