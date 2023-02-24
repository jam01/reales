package com.jam01.reales.distributor;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.exchange.Right;
import com.jam01.reales.core.exchange.Transfer;

import java.util.List;

public class CollectionTransfer extends Transfer {
    public final Resource incrementCollection;
    public final Resource decrementCollection;

    public CollectionTransfer(Resource resource, List<Right> rights, Resource incrementCollection, Resource decrementCollection) {
        super(resource, rights);
        this.incrementCollection = incrementCollection;
        this.decrementCollection = decrementCollection;
    }
}
