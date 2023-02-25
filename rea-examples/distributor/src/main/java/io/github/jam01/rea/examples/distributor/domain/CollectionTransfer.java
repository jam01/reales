package io.github.jam01.rea.examples.distributor.domain;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.exchange.Transfer;

public class CollectionTransfer extends Transfer {
    public final Resource incrementCollection;
    public final Resource decrementCollection;

    public CollectionTransfer(Resource resource, Resource incrementCollection, Resource decrementCollection) {
        super(resource);
        this.incrementCollection = incrementCollection;
        this.decrementCollection = decrementCollection;
    }
}
