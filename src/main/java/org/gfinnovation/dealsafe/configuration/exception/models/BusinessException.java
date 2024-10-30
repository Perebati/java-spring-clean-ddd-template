package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BusinessException
 * @authorNote Business Exception is for general use, throw it when you not sure
 * what caused the error.
 * @since 30/10/2024
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
