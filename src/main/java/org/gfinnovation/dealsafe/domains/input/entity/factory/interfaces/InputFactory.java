package org.gfinnovation.dealsafe.domains.input.entity.factory.interfaces;

import jakarta.validation.ValidationException;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.domains.input.entity.InputEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface InputFactory
 * @since 30/10/2024
 */

public interface InputFactory {
    InputEntity produce(UUID user_id, UUID company_id, String name, String json) throws FactoryException, ValidationException;
}
