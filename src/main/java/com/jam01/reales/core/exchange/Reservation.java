package com.jam01.reales.core.exchange;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.Value;

import java.util.Collections;
import java.util.List;

public final class Reservation {
    public static class Specification extends com.jam01.reales.core.Reservation.Specification {
        public final List<Right> rights;

        public Specification(ResourceType resourceType, Value quantity, List<Right> rights) {
            super(resourceType, quantity);
            this.rights = Collections.unmodifiableList(rights);
        }

        public Specification(ResourceType resourceType, double quantity, List<Right> rights) {
            super(resourceType, quantity);
            this.rights = Collections.unmodifiableList(rights);
        }
    }

    public static class Allocated extends com.jam01.reales.core.Reservation.Allocated {
        public final List<Right> rights;

        public Allocated(Resource resource, List<Right> rights) {
            super(resource);
            this.rights = Collections.unmodifiableList(rights);
        }
    }
}
