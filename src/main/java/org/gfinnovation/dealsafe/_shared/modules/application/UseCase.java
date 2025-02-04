package org.gfinnovation.dealsafe._shared.modules.application;

import org.apache.coyote.BadRequestException;

public interface UseCase<IN, OUT> {
    OUT execute(IN input) throws BadRequestException;
}
