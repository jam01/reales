package com.jam01.reales.core;

import java.util.Optional;

public abstract class Commitment<T extends Reservation> {
    private final CommitmentType specification;
    private final EventType eventType;
    private final T reservation;
    private final Agent provider;
    private final Agent receiver;
    private final boolean isFulfilled = false;

    public Commitment(T reservation) {
        this(reservation, null, null);
    }

    public Commitment(T reservation, Agent provider, Agent receiver) {
        this(reservation, provider, receiver, null, null);
    }

    public Commitment(T reservation, Agent provider, Agent receiver, CommitmentType specification, EventType eventType) {
        this.specification = specification;
        this.eventType = eventType;
        this.reservation = reservation;
        this.provider = provider;
        this.receiver = receiver;
    }

    public Optional<CommitmentType> specification() {
        return Optional.ofNullable(specification);
    }

    public T reservation() {
        return reservation;
    }

    public Optional<EventType> eventSpecification() {
        return Optional.ofNullable(eventType);
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }


//    public Event<Stockflow> fulfill() {
//        isFulfilled = true;
//        return new
//    }

    //    public Event<Stockflow> fulfill() {
//        isFulfilled = true;
//        EventPublisher.instanceOf().publish();
//    }

//    private Commitment(Builder builder) {
//        this.type = builder.type;
//        this.resource = builder.resource;
//        this.provider = builder.provider;
//        this.receiver = builder.receiver;
//    }

//    public static class Builder {
//        private Event.Type type;
//        private Agent provider;
//        private Agent receiver;
//        private Resource resource;
//
//        private Builder(Event.Type type) {
//            this.type = type;
//        }
//
//        public Builder ofType(Event.Type type) {
//            return new Builder(type);
//        }
//
//        public Builder ofResource(Resource resource) {
//            this.resource = resource;
//            return this;
//        }
//
//        public Builder withProvider(Agent agent) {
//            this.provider = agent;
//            return this;
//        }
//
//        public Builder withReceiver(Agent agent) {
//            this.receiver = agent;
//            return this;
//        }
//
//    }

}
