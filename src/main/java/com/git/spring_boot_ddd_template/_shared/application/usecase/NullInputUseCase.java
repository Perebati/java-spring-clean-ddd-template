package com.git.spring_boot_ddd_template._shared.application.usecase;

import com.git.spring_boot_ddd_template.exception.SystemGlobalException;

/**
 * UseCase for null input.
 *
 * @author Lucas Batista Pereira
 * @version v1.0
 * @since v1.0 (30/01/2025)
 */
public abstract class NullInputUseCase<OUT, SERVICE> {
    protected final SERVICE service;

    public NullInputUseCase(SERVICE service) {
        this.service = service;
    }

    public abstract OUT execute() throws SystemGlobalException;
}