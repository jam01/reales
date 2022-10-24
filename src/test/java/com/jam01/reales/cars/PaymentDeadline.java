package com.jam01.reales.cars;

import com.jam01.reales.core.aspects.DueDate;

import java.time.Duration;
import java.time.ZonedDateTime;

public class PaymentDeadline extends DueDate {

    public PaymentDeadline(ZonedDateTime date, Duration duration) {
        super(date, duration);
    }

    @Override
    public Object activationRule() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return true;
    }
}
