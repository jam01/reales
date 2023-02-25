package io.github.jam01.rea.examples.distributor.domain.resources;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.math.BigDecimal;

public class BankAccount extends Resource {
    public final BigDecimal amount;
    public final String name;
    public final String bank;
    public final String number;

    public BankAccount(ResourceType resourceType, String name, String bank, String number, BigDecimal amount) {
        super(resourceType, null, null);
        this.name = name;
        this.bank = bank;
        this.number = number;
        this.amount = amount;
    }
}
