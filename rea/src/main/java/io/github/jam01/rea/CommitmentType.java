package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

public abstract class CommitmentType {
    protected CommitmentType type;

    public CommitmentType(@Nullable CommitmentType type) {
        this.type = type;
    }

    protected CommitmentType() {
        this(null);
    }

    protected CommitmentType type() {
        return type;
    }
}
