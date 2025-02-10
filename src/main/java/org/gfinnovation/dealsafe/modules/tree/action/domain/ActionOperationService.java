package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe._shared.application.interfaces.GenericService;
import org.gfinnovation.dealsafe.exception.SystemGlobalException;

import java.util.UUID;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionOperationBusiness
 * @since v1.0 (30/11/2024)
 */
public interface ActionOperationService extends GenericService<ActionOperation> {
    ActionOperation create(UUID user_id, UUID company_id, String url, String message, UUID operation_id) throws SystemGlobalException;
}