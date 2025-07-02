package com.git.spring_boot_ddd_template._shared.infrastructure.repository.exception;

import com.git.spring_boot_ddd_template.exception.models.InfrastructureException;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @since v1.0 (06/02/2025)
 */
public class EntityNotFound extends InfrastructureException {
    public EntityNotFound(String message, Throwable e) {
        super(message, e);
    }
}
