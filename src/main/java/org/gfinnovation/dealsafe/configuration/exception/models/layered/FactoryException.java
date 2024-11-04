package org.gfinnovation.dealsafe.configuration.exception.models.layered;

/**
 * FactoryException is for general use, throw it when you not sure
 * what caused the error on factory level.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class FactoryException
 * @since 04/11/2024
 */

public class FactoryException extends RuntimeException {
    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}