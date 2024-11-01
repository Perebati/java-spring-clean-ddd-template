package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * Thrown when an exception on the repository happens.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryException
 * @since 30/10/2024
 */
public class RepositoryException extends InfrastructureException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
