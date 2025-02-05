package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.exception;

import org.gfinnovation.dealsafe.exception.models.InfrastructureException;

public class EntityNotFound extends InfrastructureException {
    public EntityNotFound(String message) {
        super(message);
    }
}
