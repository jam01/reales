package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

public abstract class CommitmentType {
    private final CommitmentType type;

    public CommitmentType(@Nullable CommitmentType type) {
        this.type = type;
    }

    public CommitmentType() {
        this(null);
    }

    public CommitmentType type() {
        return type;
    }
}
