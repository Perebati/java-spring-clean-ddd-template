package org.gfinnovation.dealsafe.modules.tree.action.domain;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationFactory
 * @since 30/10/2024
 */

public interface ActionOperationFactory {
    ActionOperation produce(String url, String message) throws FactoryException;
}
