package com.jam01.reales.cars;

import com.jam01.reales.core.aspects.DueDate;

public class PreferredPaymentDeadline extends DueDate {
    @Override
    public Object activationRule() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return false;
    }


}
