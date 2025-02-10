package org.gfinnovation.dealsafe._shared.application.usecase;

import org.gfinnovation.dealsafe.exception.models.ApplicationException;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class UseCase2Inputs
 * @since v1.0 (10/02/2025)
 */
public abstract class UseCase2Inputs<IN1, IN2, OUT, SERVICE> {

    protected final SERVICE service;

    public UseCase2Inputs(SERVICE service) {
        this.service = service;
    }

    public abstract OUT execute(IN1 input1, IN2 input2) throws ApplicationException;
}