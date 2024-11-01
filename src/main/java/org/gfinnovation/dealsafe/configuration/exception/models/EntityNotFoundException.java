package org.gfinnovation.dealsafe.configuration.exception.models;

/**
 * Thrown when a method didn't find what it was looking for.
 *
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @class EntityNotFoundException
 * @since 30/10/2024
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
