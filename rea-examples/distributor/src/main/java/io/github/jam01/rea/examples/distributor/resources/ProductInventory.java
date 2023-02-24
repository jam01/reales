package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;

public class ProductInventory extends Resource {
    public final double quantity;

    public ProductInventory(ResourceType specification, double quantity) {
        super(specification, null, null);
        this.quantity = quantity;
    }
}
