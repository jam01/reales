package io.github.jam01.rea.conversion;

import io.github.jam01.rea.Resource;
import io.github.jam01.rea.ResourceType;
import io.github.jam01.rea.attributes.Value;

import java.util.List;

public final class Reservation {
    public static class Specification extends io.github.jam01.rea.Reservation.Specification {
        public final List<Feature> features;

        public Specification(ResourceType resourceType, double quantity, List<Feature> features) {
            super(resourceType, quantity);
            this.features = features;
        }

        public Specification(ResourceType resourceType, Value quantity, List<Feature> features) {
            super(resourceType, quantity);
            this.features = features;
        }
    }

    public static class Allocated extends io.github.jam01.rea.Reservation.Allocated  {
        public final List<Feature> features;

        public Allocated(Resource resource, List<Feature> features) {
            super(resource);
            this.features = features;
        }
    }
}
