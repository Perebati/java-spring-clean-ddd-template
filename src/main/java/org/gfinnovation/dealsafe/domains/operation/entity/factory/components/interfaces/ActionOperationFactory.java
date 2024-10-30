package org.gfinnovation.dealsafe.domains.operation.entity.factory.components.interfaces;

import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationFactory
 * @authorNote n/a
 * @since 30/10/2024
 */
public interface ActionOperationFactory {
    ActionOperationEntity produce(UUID user_id, UUID company_id, String url, String message);
}
