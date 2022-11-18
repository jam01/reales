package com.jam01.reales.core;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Contract {
    public final List<Commitment> commitments;
    public final Set<Agent> parties;

    protected Contract(List<Commitment> commitments) {
        var parties = new HashSet<Agent>();
        for (Commitment commitment : commitments) {
            parties.add(commitment.provider);
            parties.add(commitment.receiver);
        }

        if (parties.size() < 2) throw new IllegalArgumentException("Cannot create a contract with less than two parties");

        this.parties = Collections.unmodifiableSet(parties);
        this.commitments = Collections.unmodifiableList(commitments);
    }

    public boolean isFulfilled() {
        for (Commitment commitment : commitments) {
            if (!commitment.isFulfilled()) return false;
        }
        return true;
    }
}
