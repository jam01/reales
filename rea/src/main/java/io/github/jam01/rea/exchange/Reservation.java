package io.github.jam01.rea.exchange;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.util.Collections;
import java.util.List;

public final class Reservation {
    public static class Specification extends io.github.jam01.rea.Reservation.Specification {
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

    public static class Allocated extends io.github.jam01.rea.Reservation.Allocated {
        public final List<Right> rights;

        public Allocated(Resource resource, List<Right> rights) {
            super(resource);
            this.rights = Collections.unmodifiableList(rights);
        }
    }
}
