package org.gfinnovation.dealsafe._shared.modules.infrastructure.repository.exception;

import org.gfinnovation.dealsafe.exception.models.InfrastructureException;

/**
 * @author Lucas Batista Pereira
 * @version v1.1
 * @class EntityNotFound
 * @since v1.0 (06/02/2025)
 */
public class EntityNotFound extends InfrastructureException {
    public EntityNotFound(String message) {
        super(message);
    }
}
