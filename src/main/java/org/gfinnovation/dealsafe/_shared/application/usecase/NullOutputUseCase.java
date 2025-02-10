package org.gfinnovation.dealsafe._shared.application.usecase;

/**
 * UseCase for null output.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class UseCase
 * @since v1.0 (30/01/2025)
 */
public abstract class NullOutputUseCase<IN, SERVICE> {
    protected final SERVICE service;
    protected NullOutputUseCase(SERVICE service) {
        this.service = service;
    }

    public abstract void execute(IN input);
}
