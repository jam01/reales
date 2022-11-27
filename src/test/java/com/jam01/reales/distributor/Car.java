package com.jam01.reales.distributor;

import com.jam01.reales.core.Resource;
import com.jam01.reales.application.components.identifier.Generator;
import com.jam01.reales.application.components.identifier.Identifier;

public class Car extends Resource {
    public static Generator idSetup = Identifier.sequence(0, 1);

    public Identifier id;

    public Car() {
        this.id = idSetup.next();
    }
}
