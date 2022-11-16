package com.jam01.reales.common;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Value;

public class Cash extends Resource {
    public final Value amount;

    public Cash(Value amount) {
        this.amount = amount;
    }
}
