package org.gfinnovation.dealsafe.exception.models;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * RepositoryException is for general use, throw it when you not sure
 * what caused the error on repository level.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryException
 * @since 30/10/2024
 */
public class InfrastructureException extends SystemGlobalException {
    public InfrastructureException(String message) {
        super(message);
    }
}
