package org.gfinnovation.dealsafe.modules.exception.models.layered;

/**
 * RepositoryException is for general use, throw it when you not sure
 * what caused the error on repository level.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class RepositoryException
 * @since 30/10/2024
 */
public class RepositoryException extends ServiceException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
