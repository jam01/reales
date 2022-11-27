package com.jam01.reales.distributor.resources;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.ResourceType;

public class ProductItem extends Resource {
    public final double quantity;

    public ProductItem(ResourceType specification, double quantity) {
        super(specification, null);
        this.quantity = quantity;
    }
}
