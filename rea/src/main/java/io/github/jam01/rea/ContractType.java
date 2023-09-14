package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class ContractType {
    private @Nullable ContractType type;

    public ContractType() {
        this(null);
    }

    public ContractType(@Nullable ContractType type) {
        this.type = type;
    }

    public Optional<ContractType> type() {
        return Optional.ofNullable(type);
    }
}
