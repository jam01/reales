package com.jam01.reales.distributor.agents;

import com.jam01.reales.core.Agent;
import com.jam01.reales.application.components.identifier.Generator;
import com.jam01.reales.application.components.identifier.Identifier;
import com.jam01.reales.application.components.identifier.Validator;

public class Employee extends Agent {
    public static Generator idSequence = Identifier.sequence(0, 1);
    public static Validator phoneFormat = Identifier.regularExpression("0-9");

    public final Identifier id;
    public final Identifier ssn;
    public final Identifier phone;

    public Employee(Identifier ssn, Identifier phone) {
        this.id = idSequence.next();
        this.ssn = ssn;

        phoneFormat.validate(phone);
        this.phone = phone;
    }
}
