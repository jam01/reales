package com.jam01.reales.core.conversion;

import com.jam01.reales.core.Resource;
import com.jam01.reales.core.ResourceType;
import com.jam01.reales.core.attributes.Value;

import java.util.List;

public final class Reservation {
    public static class Specification extends com.jam01.reales.core.Reservation.Specification {
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

    public static class Allocated extends com.jam01.reales.core.Reservation.Allocated  {
        public final List<Feature> features;

        public Allocated(Resource resource, List<Feature> features) {
            super(resource);
            this.features = features;
        }
    }
}
