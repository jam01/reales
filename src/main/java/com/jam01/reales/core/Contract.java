package com.jam01.reales.core;

import java.util.Collection;

public abstract class Contract {
    public abstract Collection<Commitment<Reservation>> commitments();
}
