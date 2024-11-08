package org.gfinnovation.dealsafe.configuration.exception.models.layered;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class BusinessAuthenticationException
 * @since 08/11/2024
 */

public class BusinessAuthenticationException extends BusinessException {
    public BusinessAuthenticationException(String message) {
        super(message);
    }

    public BusinessAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
