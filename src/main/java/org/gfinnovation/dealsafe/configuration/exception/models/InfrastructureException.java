package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * Thrown when an exception in the infra happens.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InfrastructureException
 * @since 30/10/2024
 */
public class InfrastructureException extends RuntimeException {
    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}

