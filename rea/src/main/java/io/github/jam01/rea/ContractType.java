package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

public abstract class ContractType {
    private final ContractType type;

    public ContractType(@Nullable ContractType type) {
        this.type = type;
    }

    public ContractType() {
        this(null);
    }

    public ContractType type() {
        return type;
    }
}
