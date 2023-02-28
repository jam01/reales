package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

// derive claims based on commitments fulfilled
// instantiate new commitments (e.g.: penalties, installments)
public abstract class Contract extends Agreement {
    private final @Nullable ContractType specification;
    private final List<? extends Commitment> commitments;

    protected Contract(List<? extends Commitment> commitments) {
        this(null, commitments);
    }

    protected Contract(@Nullable ContractType type, List<? extends Commitment> commitments) {
        this.specification = type;
        this.commitments = commitments != null ? Collections.unmodifiableList(commitments) : Collections.emptyList();
    }

    public boolean isComplete() {
        for (Commitment commitment : commitments) {
            if (!commitment.isFulfilled) return false;
        }

        return true;
    }

    public Optional<ContractType> type() {
        return Optional.ofNullable(specification);
    }

    // TODO: 2/25/23 memoize
    public Set<Agent> parties() {
        var toReturn = new HashSet<Agent>();
        for (Commitment commitment : commitments) {
            toReturn.add(commitment.provider);
            toReturn.add(commitment.receiver);
        }

        return toReturn;
    }

    protected abstract Contract withCommitments(List<? extends Commitment> commitments1);

    protected Contract withUpdatedCommitments(List<? extends Commitment> commitments1) {
        return withCommitments(commitments1);
    }

    public Contract extendCommitments(List<? extends Commitment> commitments1) {
        Objects.requireNonNull(commitments1);

        List<Commitment> list = new ArrayList<>(commitments.size() + commitments1.size());
        list.addAll(commitments);
        list.addAll(commitments1);

        return this.withCommitments(list);
    }
}
