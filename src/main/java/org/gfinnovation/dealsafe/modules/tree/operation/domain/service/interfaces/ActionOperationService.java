package org.gfinnovation.dealsafe.modules.tree.operation.domain.service.interfaces;

import org.gfinnovation.dealsafe._shared.modules.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.configuration.exception.models.layered.BusinessException;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ActionOperation;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationBusiness
 * @since 30/10/2024
 */
public interface ActionOperationService extends GenericService<ActionOperation> {
    ActionOperation create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws BusinessException;
}