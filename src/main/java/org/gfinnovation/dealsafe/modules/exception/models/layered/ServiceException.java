package org.gfinnovation.dealsafe.modules.exception.models.layered;

/**
 * ServiceException is for general use, throw it when you not sure
 * what caused the error on business level.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class ServiceException
 * @since 30/10/2024
 */
public class ServiceException extends DomainException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
