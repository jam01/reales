package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.Collections;
import java.util.List;

public class Transfer extends Stockflow {
    public final List<Right> rights;

    public Transfer(Resource resource, List<Right> rights) {
        super(resource);
        this.rights = Collections.unmodifiableList(rights);
    }
}
