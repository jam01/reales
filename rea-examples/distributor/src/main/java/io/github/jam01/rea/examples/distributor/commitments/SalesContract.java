package io.github.jam01.rea.examples.distributor.commitments;

import io.github.jam01.rea.Commitment;
import io.github.jam01.rea.Contract;
import io.github.jam01.rea.ContractType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class SalesContract extends Contract {
    public SalesContract(List<Commitment> commitments) {
        super(commitments);
    }

    public SalesContract(@Nullable ContractType type, List<Commitment> commitments) {
        super(type, commitments);
    }

    @Override
    protected Contract withCommitments(List<? extends Commitment> commitments1) {
        throw new UnsupportedOperationException("This implementation cannot add commitments after creating the Contract");
    }
}
