package io.github.jam01.reales.domain.commands;

import io.github.jam01.reales.domain.FContract;

public final class CreateContract extends Command {
    public final FContract contract;

    public CreateContract(FContract contract) {
        this.contract = contract;
    }
}
