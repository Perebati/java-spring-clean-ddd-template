package org.gfinnovation.dealsafe._shared.modules.application.interfaces;

import org.apache.coyote.BadRequestException;

public interface UseCase<I, O> {
    O execute(I input) throws BadRequestException;
}
