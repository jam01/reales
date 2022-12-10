package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.Stockflow;

import java.util.List;

public class Transfer extends Stockflow implements HasRights {
    private final List<Right> rights;

    public Transfer(Resource resource, List<Right> rights) {
        super(resource);
        this.rights = rights;
    }

    @Override
    public List<Right> getRights() {
        return rights;
    }
}
