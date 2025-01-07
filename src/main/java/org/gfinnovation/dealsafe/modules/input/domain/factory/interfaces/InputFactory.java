package org.gfinnovation.dealsafe.modules.input.domain.factory.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.input.domain.InputEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface InputFactory
 * @since 30/10/2024
 */

public interface InputFactory {
    InputEntity produce(UUID user_id, UUID company_id, String name, String json) throws FactoryException;
}
