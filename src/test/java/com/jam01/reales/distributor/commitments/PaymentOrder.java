package com.jam01.reales.distributor.commitments;

import com.jam01.reales.core.Agent;
import com.jam01.reales.core.Commitment;
import com.jam01.reales.core.Reservation;

import java.util.List;

public class PaymentOrder extends Commitment {
    public PaymentOrder(Agent provider, Agent receiver, List<Reservation> reservations) {
        super(provider, receiver, reservations);
    }
}
