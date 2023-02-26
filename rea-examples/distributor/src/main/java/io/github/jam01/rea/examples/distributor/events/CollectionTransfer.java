package io.github.jam01.rea.examples.distributor.events;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.examples.distributor.resources.CollectionResource;
import io.github.jam01.rea.exchange.Transfer;

public class CollectionTransfer<T extends Number> extends Transfer {
    public final Resource incrementCollection;
    public final Resource decrementCollection;

    // tradeoff for example simplicity:
    // - making the changes on the collections at instantiation instead of when the events are 'saved'
    public CollectionTransfer(CollectionResource<T> productStock, CollectionResource<T> incrementCollection, CollectionResource<T> decrementCollection) {
        super(productStock);
        this.incrementCollection = incrementCollection;
        this.decrementCollection = decrementCollection;

        if (incrementCollection != null) {
            incrementCollection.add(productStock.quantity());
        } else if (decrementCollection != null) {
            decrementCollection.subtract(productStock.quantity());
        }
        // possible improvements:
        // - may check that the collections don't go negative
        // - kick off requisition business events if collections pass a particular threshold
    }
}
