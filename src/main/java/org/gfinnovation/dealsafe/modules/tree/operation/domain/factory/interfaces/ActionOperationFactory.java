package org.gfinnovation.dealsafe.modules.tree.operation.domain.factory.interfaces;

import org.gfinnovation.dealsafe.configuration.exception.models.layered.FactoryException;
import org.gfinnovation.dealsafe.modules.tree.operation.domain.ActionOperation;

/**
 * @author Lucas Batista Pereira
 * @version DealSafe_alpha_v1
 * @interface ActionOperationFactory
 * @since 30/10/2024
 */

public interface ActionOperationFactory {
    ActionOperation produce(String url, String message) throws FactoryException;
}
