package com.jam01.reales.core;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public abstract class Commitment {
    public final CommitmentType specification;
    public final EventType eventType;
    public final List<Reservation> reservations;
    public final Agent provider;
    public final Agent receiver;
    private final boolean isFulfilled = false;

    protected Commitment(Agent provider, Agent receiver, List<? extends Reservation> reservations) {
        this(null, provider, receiver, reservations, null);
    }

    protected Commitment(CommitmentType specification, Agent provider, Agent receiver, List<? extends Reservation> reservations, EventType eventType) {
        this.specification = specification;
        this.eventType = eventType;
        this.reservations = Collections.unmodifiableList(reservations);
        this.provider = provider;
        this.receiver = receiver;
    }

    public Optional<CommitmentType> specification() {
        return Optional.ofNullable(specification);
    }

    public Optional<EventType> eventSpecification() {
        return Optional.ofNullable(eventType);
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    public Commitment fulfilledBy(Event<? extends Stockflow> event) {
        // somehow check that the stockflows in this event fulfill this commitment
        return this;
    }


//    public Event<Stockflow> fulfill() {
//        isComplete = true;
//        return new
//    }

    //    public Event<Stockflow> fulfill() {
//        isComplete = true;
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
