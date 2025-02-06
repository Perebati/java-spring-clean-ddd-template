package org.gfinnovation.dealsafe._shared.modules.application.usecase;

import org.apache.coyote.BadRequestException;

/**
 * Abstract class for UseCase,
 * every single UseCase should extend from this or its variations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class UseCase
 * @since v1.0 (30/01/2025)
 */
public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input) throws BadRequestException;
}
