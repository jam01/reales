package io.github.jam01.rea.examples.distributor.agents;

import io.github.jam01.rea.Agent;

public final class Enterprise extends Agent {
    private static Enterprise INSTANCE;

    private Enterprise() { }

    public static synchronized Enterprise getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Enterprise();
        }
        return INSTANCE;
    }
}
