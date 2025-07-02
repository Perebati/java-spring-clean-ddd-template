package com.git.spring_boot_ddd_template._shared.application.usecase;

import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * UseCase for null output.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since v1.0 (30/01/2025)
 */
public abstract class NullOutputUseCase<IN, SERVICE> {
    protected final SERVICE service;
    protected NullOutputUseCase(SERVICE service) {
        this.service = service;
    }

    public abstract void execute(IN input) throws SystemGlobalException;
}
