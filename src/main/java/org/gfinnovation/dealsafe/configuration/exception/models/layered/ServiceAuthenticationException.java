package org.gfinnovation.dealsafe.configuration.exception.models.layered;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ServiceAuthenticationException
 * @since 08/11/2024
 */

public class ServiceAuthenticationException extends ServiceException {
    public ServiceAuthenticationException(String message) {
        super(message);
    }

    public ServiceAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
