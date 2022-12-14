package com.jam01.reales.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// derive claims based on commitments fulfilled
// instantiate new commitments (e.g.: penalties, installments)
public abstract class Contract extends Agreement {
    private final ContractType specification;
    public final List<Commitment> commitments;

    protected Contract(List<Commitment> commitments) {
        this(null, commitments);
    }

    protected Contract(ContractType specification, List<Commitment> commitments) {
        this.specification = specification;
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

    public Set<Agent> parties() {
        var toReturn = new HashSet<Agent>();
        for (Commitment commitment : commitments) {
            toReturn.add(commitment.provider);
            toReturn.add(commitment.receiver);
        }

        return toReturn;
    }
}
