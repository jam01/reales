package com.jam01.reales.core.aspects;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class DueDate {
    public static byte ACTIVE = 0;
    public static byte DUE = 1;
    public static byte COMPLETED = 2;
    public static byte CANCELLED = 3;
    private final Duration duration;
    private ZonedDateTime date;
    private byte state = ACTIVE;

    public DueDate(ZonedDateTime date, Duration duration) {
        this.date = date;
        this.duration = duration;
    }

    public abstract Object activationRule();

    public boolean isEditable() {
        return false;
    }

    public void setDate(ZonedDateTime date) {
        if (!isEditable())
            throw new UnsupportedOperationException(this.getClass().getSimpleName() + " is not editable.");

        this.date = date;
    }

    public ZonedDateTime date() {
        return date;
    }

    public Duration duration() {
        return duration;
    }

    public byte state() {
        return state;
    }

    public void complete() {
        if (state == CANCELLED)
            throw new IllegalStateException("Cannot complete a cancelled due date.");

        state = COMPLETED;
    }


    public void cancel() {
        if (state != ACTIVE)
            throw new IllegalStateException("Can only cancel an active due date.");

        state = CANCELLED;
    }

}
