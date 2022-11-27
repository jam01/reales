package com.jam01.reales.distributor.resources;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.Value;

public class BankAccount extends Resource {
    public final Value amount;
    public final String name;
    public final String bank;
    public final String number;

    public BankAccount(ResourceType resourceType, String name, String bank, String number, Value amount) {
        super(resourceType, null);
        this.name = name;
        this.bank = bank;
        this.number = number;
        this.amount = amount;
    }
}
