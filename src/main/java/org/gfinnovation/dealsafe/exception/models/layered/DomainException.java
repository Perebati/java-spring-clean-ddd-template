package org.gfinnovation.dealsafe.exception.models.layered;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class DomainException
 * @since 08/11/2024
 */

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
