package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * Business Exception is for general use, throw it when you not sure
 * what caused the error.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BusinessException
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
