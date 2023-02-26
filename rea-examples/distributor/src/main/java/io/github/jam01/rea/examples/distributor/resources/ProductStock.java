package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.attributes.Value;

public class ProductStock<T extends Number> extends CollectionResource<T> {

    public ProductStock(ProductType<T> specification, Value<T> quantity) {
        super(specification, null, null, quantity);
    }

    public ProductStock(ProductType<T> type, T quantity) {
        super(type, null, null, Value.of(quantity, type.unit));
    }
}
