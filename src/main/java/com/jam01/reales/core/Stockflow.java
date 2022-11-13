package com.jam01.reales.core;


public abstract class Stockflow {
    public final Direction direction;
    public final Resource resource;

    private Stockflow(Direction direction, Resource resource) {
        this.direction = direction;
        this.resource = resource;
    }

    public enum Direction {
        INCREMENT, DECREMENT
    }

    public static abstract class Increment extends Stockflow {
        private Increment(Direction direction, Resource resource) {
            super(direction, resource);
        }

        public Increment(Resource resource) {
            this(Direction.INCREMENT, resource);
        }
    }

    public static abstract class Decrement extends Stockflow {
        private Decrement(Direction direction, Resource resource) {
            super(direction, resource);
        }

        public Decrement(Resource resource) {
            this(Direction.INCREMENT, resource);
        }
    }
}
