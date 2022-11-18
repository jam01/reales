package com.jam01.reales.core;

import java.util.List;

public abstract class ContractType extends Contract {
    protected ContractType(List<Commitment> commitments) {
        super(commitments);
    }
}
