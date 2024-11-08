package org.gfinnovation.dealsafe.configuration.exception.models.layered;

/**
 * Thrown when a method didn't find what it was looking for.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class EntityNotFoundException
 * @since 30/10/2024
 */
public class RepositoryEntityNotFoundException extends RepositoryException {
    public RepositoryEntityNotFoundException(String message) {
        super(message);
    }
}
