package org.gfinnovation.dealsafe._shared.application.usecase;

import org.gfinnovation.dealsafe.exception.models.ApplicationException;

/**
 * Abstract class for UseCase,
 * every single UseCase should extend from this or its variations.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class UseCase
 * @since v1.0 (30/01/2025)
 */
public abstract class UseCase<IN, OUT, SERVICE>{
    protected final SERVICE service;

    public UseCase(SERVICE service) {
        this.service = service;
    }

    public abstract OUT execute(IN input) throws ApplicationException;
}