package io.github.jam01.reales;

import io.github.jam01.rea.Event;

import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.SubmissionPublisher;

public class EventPublisher {
    private static EventPublisher INSTANCE;
    private final SubmissionPublisher<Event<?>> publisher = new SubmissionPublisher<>();

    public static synchronized EventPublisher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventPublisher();
        }
        return INSTANCE;
    }

    public void publish(Event<?> object) {
        publisher.submit(object);
    }

    public Publisher<Event<?>> getEvents() {
        return publisher;
    }
}
