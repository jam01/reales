package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.attributes.Value;

import java.util.Objects;

public class Reservation {
    private Reservation() {
    }

    public boolean isAllocated() {
        return false;
    }

    public static class Specification extends Reservation {
        protected ResourceType resourceType;
        protected Value<?> quantity;

        public Specification(ResourceType resourceType, Value<?> quantity) {
            Objects.requireNonNull(resourceType);
            Objects.requireNonNull(quantity);

            this.resourceType = resourceType;
            this.quantity = quantity;
        }

        public ResourceType resourceType() {
            return resourceType;
        }

        public Value<?> quantity() {
            return quantity;
        }

        public Allocated allocated(Resource resource) {
            requireEqType(resource);

            return new Allocated(resource);
        }

        protected void requireEqType(Resource resource) {
            var allocType = resource.type();
            if (allocType.isEmpty())
                throw new IllegalArgumentException("Cannot allocate a resource without a type if a type was previously specified");

            if (!resourceType.equals(allocType.get()))
                throw new IllegalArgumentException("Cannot allocate a resource of a different type as reserved");
        }
    }

    public static class Allocated extends Reservation {
        protected Resource resource;

        public Allocated(Resource resource) {
            Objects.requireNonNull(resource);

            this.resource = resource;
        }

        public Resource resource() {
            return resource;
        }

        @Override
        public boolean isAllocated() {
            return true;
        }
    }
}
