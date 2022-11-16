package com.jam01.reales.core;

import java.util.List;

public abstract class Contract {
    public abstract List<Commitment<Reservation>> commitments();

    public boolean isFulfilled() {
        for (Commitment<Reservation> commitment : commitments()) {
            if (!commitment.isFulfilled()) return false;
        }
        return true;
    }
}
