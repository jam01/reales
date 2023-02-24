package io.github.jam01.rea.exchange;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.Stockflow;

import java.util.Collections;
import java.util.List;

public class Transfer extends Stockflow {
    public final List<Right> rights;

    public Transfer(Resource resource, List<Right> rights) {
        super(resource);
        this.rights = rights != null ? Collections.unmodifiableList(rights) : Collections.emptyList();
    }
}
