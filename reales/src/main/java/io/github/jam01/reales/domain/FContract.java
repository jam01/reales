package io.github.jam01.reales.domain;

import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Contract;
import io.github.jam01.rea.ContractType;
import io.github.jam01.rea.Result;
import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class FContract extends io.github.jam01.rea.Contract {
    // TODO: 3/19/23 withCommitments
    // TODO: 3/19/23 extendCommitments
    // TODO: 3/19/23 complete
    // TODO: 3/19/23 isComplete
    // TODO: 3/19/23 test methods for withCommitments, extendCommitments, complete

    private final Map<String, Attribute<?>> attributes;
    private final Function<FContract, Void> invariants;
    private final BiFunction<FContract, List<Commitment>, Result<FContract>> updateCommitmentsFunc;

    public FContract(List<? extends Commitment> commitments) {
        this(null, commitments,
                null,
                null,
                null);
    }

    public FContract(@Nullable ContractType type, List<? extends Commitment> commitments,
                     Map<String, Attribute<?>> attributes,
                     Function<FContract, Void> invariants,
                     BiFunction<FContract, List<Commitment>, Result<FContract>> updateCommitmentsFunc) {
        super(type, commitments);

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants;

        if (invariants != null) invariants.apply(this);

        this.updateCommitmentsFunc = updateCommitmentsFunc != null ? updateCommitmentsFunc :
                (contract, commitments1) -> null;
    }

    @Override
    protected Result<? extends Contract> withCommitments(List<? extends Commitment> commitments1) {
        return null;
    }
}
