package io.github.jam01.reales.domain;

import io.github.jam01.rea.Group;
import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class CollectionResource<T extends Number> extends FResource {
    public final Value<T> quantity;

    public CollectionResource(@Nullable ResourceType type, List<? extends Group> groups, List<? extends Resource> components,
                              Value<T> quantity,
                              Map<String, Attribute<?>> attributes,
                              Function<FResource, Void> invariants) {
        super(type, groups, components,
                attributes, invariants);

        this.quantity = quantity;
    }

    // only supports int and BigDecimal
    // should be offloaded to a addition service?
    @SuppressWarnings("unchecked")
    public CollectionResource<T> add(Value<T> other) {
        if (!quantity.unit().equals(other.unit())) {
            throw new IllegalArgumentException("Cannot add CollectionResources of different units");
        }

        Number qty = quantity.value();
        var otherQty = other.value();

        if (qty instanceof Integer) {
            qty = ((int) qty) + ((int) otherQty);
        } else if (qty instanceof BigDecimal) {
            qty = ((BigDecimal) qty).add((BigDecimal) otherQty);
        } else {
            throw new IllegalArgumentException("CollectionResource only supports integers and BigDecimal numbers");
        }

        return new CollectionResource<>(type, groups, components, Value.of(((T) qty), quantity.unit()), attributes, invariants);
    }

    @SuppressWarnings("unchecked")
    public Result<T> remove(Value<T> other) {
        if (!quantity.unit().equals(other.unit())) {
            throw new IllegalArgumentException("Cannot add CollectionResources of different units");
        }

        Number qty = quantity.value();
        var otherQty = other.value();

        if (qty instanceof Integer) {
            qty = ((int) qty) - ((int) otherQty);
        } else if (qty instanceof BigDecimal) {
            qty = ((BigDecimal) qty).subtract((BigDecimal) otherQty);
        } else {
            throw new IllegalArgumentException("CollectionResource only supports integers and BigDecimal numbers");
        }

        return new Result<>(new CollectionResource<>(type, groups, components, Value.of(((T) qty), quantity.unit()), attributes, invariants),
                new CollectionResource<>(type, groups, components, other, attributes, invariants));
    }

    public record Result<U extends Number>(CollectionResource<U> original,
                                           CollectionResource<U> removed) { }
}
