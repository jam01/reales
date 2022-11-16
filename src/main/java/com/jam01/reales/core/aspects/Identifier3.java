package com.jam01.reales.core.aspects;

import java.util.HashMap;
import java.util.Map;

public class Identifier3 {
    public int id;
    private static Map<String, Integer> lastId = new HashMap<>();

    public Identifier3(String seq, int seed, int step) {
        if (lastId.containsKey(seq)) {
            this.id = lastId.get(seq) + step;
        } else {
            this.id = seed;
        }

        lastId.put(seq, this.id);
    }
}
