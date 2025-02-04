package org.gfinnovation.dealsafe._shared.modules.application.usecase;

import org.apache.coyote.BadRequestException;

public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input) throws BadRequestException;
}
