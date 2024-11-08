package org.gfinnovation.dealsafe.domains.operation.application.business.components.interfaces;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericBusiness;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.domains.operation.entity.ActionOperationEntity;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationBusiness
 * @since 30/10/2024
 */
public interface ActionOperationBusiness extends GenericBusiness<ActionOperationEntity> {
    ActionOperationEntity create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException;
}
