package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class CommitmentType {
    protected @Nullable CommitmentType type;

    protected CommitmentType() {
        this(null);
    }

    public CommitmentType(@Nullable CommitmentType type) {
        this.type = type;
    }

    protected Optional<CommitmentType> type() {
        return Optional.ofNullable(type);
    }
}
