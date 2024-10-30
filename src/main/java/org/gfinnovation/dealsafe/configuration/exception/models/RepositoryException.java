package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryException
 * @authorNote throw it when an exception on the repository happens.
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
