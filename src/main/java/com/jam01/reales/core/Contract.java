package com.jam01.reales.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class Contract {
    private final ContractType specification;
    public final List<Commitment> commitments;

    protected Contract(ContractType specification, List<Commitment> commitments) {
        this.specification = specification;
        this.commitments = Collections.unmodifiableList(commitments);
    }

    protected Contract(List<Commitment> commitments) {
        this(null, commitments);
    }

    public boolean isFulfilled() {
        for (Commitment commitment : commitments) {
            if (!commitment.isFulfilled()) return false;
        }
        return true;
    }

    public Optional<ContractType> specification() {
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
