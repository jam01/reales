package com.jam01.reales.core;

public abstract class Reservation {

    public boolean isAllocated() {
        return false;
    }

    public static class Specification extends Reservation {
        public final ResourceType resourceType;

        public final Value quantity;

        public Specification(ResourceType resourceType, double quantity) {
            this.resourceType = resourceType;
            this.quantity = Value.of(quantity, resourceType.unit);
        }

        public Specification(ResourceType resourceType, Value quantity) {
            if (!resourceType.unit.equals(quantity.unit()))
                throw new IllegalArgumentException("Cannot reserve a resource with an unit different than the resource type's");

            this.resourceType = resourceType;
            this.quantity = quantity;
        }

        // should be overridden if subclassed
        public Allocated allocated(Resource resource) {
            var allocType = resource.type();
            if (allocType.isEmpty())
                throw new IllegalArgumentException("Cannot allocate a resource without a type if a type was previously specified");
            if (!resourceType.equals(allocType.get()))
                throw new IllegalArgumentException("Cannot allocate a resource of a different type as reserved");
            return new Allocated(resource);
        }
    }

    public static class Allocated extends Reservation {
        public final Resource resource;

        public Allocated(Resource resource) {
            this.resource = resource;
        }

        @Override
        public boolean isAllocated() {
            return true;
        }
    }
}
