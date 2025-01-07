package org.gfinnovation.dealsafe.modules.operation.domain.factory.components.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.operation.domain.ActionOperationEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationFactory
 * @since 30/10/2024
 */

public interface ActionOperationFactory {
    ActionOperationEntity produce(UUID user_id, UUID company_id, String url, String message) throws FactoryException;
}
