package io.github.jam01.rea.examples.distributor;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.exchange.Right;
import io.github.jam01.rea.exchange.Transfer;

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
