package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.Group;
import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;

import java.math.BigDecimal;
import java.util.List;

import io.github.jam01.rea.attributes.Value;

public class CollectionResource<T extends Number> extends Resource {
    private Value<T> quantity;

    // tradeoff for example simplicity:
    // - overriding the immutable nature of the Resources, avoids requiring entity versions or nonces
    public CollectionResource(ResourceType type, List<Group> groups, List<? extends Resource> components, Value<T> quantity) {
        super(type, groups, components);
        this.quantity = quantity;
    }

    @SuppressWarnings("unchecked")
    synchronized public void add(Value<T> other) {
        if (!quantity.unit().equals(other.unit())) {
            throw new IllegalArgumentException("Cannot add CollectionResources of different units");
        }

        var qty = quantity.value();
        var otherQty = other.value();

        if (qty instanceof Integer) {
            Integer res = ((Integer) qty) + ((Integer) otherQty);
            qty = ((T) res);
        } else if (qty instanceof BigDecimal) {
            BigDecimal res = ((BigDecimal) qty).add(((BigDecimal) otherQty));
            qty = ((T) res);
        } else {
            throw new IllegalArgumentException("CollectionResource only supports integers and BigDecimal numbers");
        }

        quantity = Value.of(qty, quantity.unit());
    }

    @SuppressWarnings("unchecked")
    synchronized public void subtract(Value<T> other) {
        if (!quantity.unit().equals(other.unit())) {
            throw new IllegalArgumentException("Cannot add CollectionResources of different units");
        }

        var qty = quantity.value();
        var otherQty = other.value();

        if (qty instanceof Integer) {
            Integer res = ((Integer) qty) - ((Integer) otherQty);
            qty = ((T) res);
        } else if (qty instanceof BigDecimal) {
            BigDecimal res = ((BigDecimal) qty).subtract(((BigDecimal) otherQty));
            qty = ((T) res);
        } else {
            throw new IllegalArgumentException("CollectionResource only supports integers and BigDecimal numbers");
        }

        quantity = Value.of(qty, quantity.unit());
    }

    public Value<T> quantity() {
        return quantity;
    }
}
