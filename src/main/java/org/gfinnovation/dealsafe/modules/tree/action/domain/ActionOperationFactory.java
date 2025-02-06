package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe.exception.SystemGlobalException;

/**
 * @author Lucas Batista Pereira
 * @version v1.0
 * @class ActionOperationFactory
 * @since v1.0 (30/11/2024)
 */

public interface ActionOperationFactory {
    ActionOperation produce(String url, String message) throws SystemGlobalException;
}
