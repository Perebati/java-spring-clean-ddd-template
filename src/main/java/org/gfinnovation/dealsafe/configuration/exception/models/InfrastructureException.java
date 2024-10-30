package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class InfrastructureException
 * @authorNote throw it when an exception in the infra happens.
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

