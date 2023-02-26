package io.github.jam01.rea.examples.distributor.resources;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;

public class BankAccount extends CollectionResource<BigDecimal> {
    public final String name;
    public final String bank;
    public final String number;

    public BankAccount(MoneyType resourceType, String name, String bank, String number, BigDecimal quantity) {
        super(resourceType, null, null, Value.of(quantity, resourceType.unit));
        this.name = name;
        this.bank = bank;
        this.number = number;
    }
}
