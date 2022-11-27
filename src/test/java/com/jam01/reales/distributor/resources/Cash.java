package com.jam01.reales.distributor.resources;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.Value;

public class Cash extends Resource {
    public final Value amount;
    public final String name;

    public Cash(ResourceType specification, String name, Value amount) {
        super(specification, null);
        this.name = name;
        this.amount = amount;
    }
}
